/**
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 *		SAUL PIÑA - SAULJP07@GMAIL.COM
 *		2014
 *
 *		COLLABORATOR: JORGE PARRA - THEJORGEMYLIO@GMAIL.COM
 */

package app.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.PointLight;
import javax.media.j3d.Screen3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import app.Translate;
import app.simulation.Agent;
import app.simulation.Cell;
import app.simulation.Configuration;
import app.simulation.Rule;
import app.simulation.Simulation;
import app.util.Util3D;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Simulation3D extends Simulation {

	private Canvas3D canvas3D;
	private SimpleUniverse universe;
	private BranchGroup branchGroup;
	private TransformGroup transformGroupLattice;
	private OffScreenCanvas3D offScreenCanvas3D;
	private TransformGroup transformGroupGeneric;
	private ViewUpdater viewUpdater;

	public Simulation3D(ViewUpdater viewUpdater, Configuration configuration, Cell initialCell, Rule[] rules) {
		super(configuration, initialCell, rules);
		this.canvas3D = viewUpdater.getCanvas3D();
		this.viewUpdater = viewUpdater;
		init();
	}

	public void init() {
		universe = new SimpleUniverse(canvas3D);
		universe.getViewingPlatform().setNominalViewingTransform();

		branchGroup = new BranchGroup();
		branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		transformGroupGeneric = new TransformGroup();
		transformGroupGeneric.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Transform3D scale = new Transform3D();
		scale.setScale(0.3);
		transformGroupGeneric.setTransform(scale);

		transformGroupLattice = new TransformGroup();
		transformGroupLattice.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Transform3D translation = new Transform3D();
		translation.setTranslation(new Vector3d(-getCoordinate(getLatticeSize()) / 2, -getCoordinate(getLatticeSize()) / 2, -getCoordinate(getLatticeSize()) / 2));
		transformGroupLattice.setTransform(translation);

		transformGroupGeneric.addChild(transformGroupLattice);
		branchGroup.addChild(transformGroupGeneric);

		MouseRotate mouseRotate = new MouseRotate();
		mouseRotate.setTransformGroup(transformGroupGeneric);
		mouseRotate.setSchedulingBounds(new BoundingSphere());

		MouseWheelZoom mouseWheelZoom = new MouseWheelZoom();
		mouseWheelZoom.setTransformGroup(transformGroupGeneric);
		mouseWheelZoom.setSchedulingBounds(new BoundingSphere());
		mouseWheelZoom.setFactor(-.1);

		branchGroup.addChild(mouseRotate);
		branchGroup.addChild(mouseWheelZoom);

		for (Agent agent : getAgents()) {
			Agent3D agent3D = (Agent3D) agent;
			transformGroupLattice.addChild(agent3D.getTransformGroup());
		}

		for (int x = 0; x < getLatticeSize(); x++) {
			for (int y = 0; y < getLatticeSize(); y++) {
				for (int z = 0; z < getLatticeSize(); z++) {
					Cell3D cell3D = (Cell3D) getLattice()[x][y][z];
					Transform3D t3D = Util3D.createTransform3D(new Vector3d(getCoordinate(x), getCoordinate(z), getCoordinate(y)), null, 0, 0, 0);
					cell3D.getTransformGroup().setTransform(t3D);
					transformGroupLattice.addChild(cell3D.getTransformGroup());
					cell3D.updateColor();
				}
			}
		}

		transformGroupGeneric.addChild(Util3D.createBackground(new Color(0, 204, 255)));
		universe.addBranchGraph(branchGroup);

		offScreenCanvas3D = new OffScreenCanvas3D(SimpleUniverse.getPreferredConfiguration());

		Screen3D sOn = canvas3D.getScreen3D();
		Screen3D sOff = offScreenCanvas3D.getScreen3D();
		Dimension dim = sOn.getSize();
		sOff.setSize(dim);
		sOff.setPhysicalScreenWidth(sOn.getPhysicalScreenWidth());
		sOff.setPhysicalScreenHeight(sOn.getPhysicalScreenHeight());
		universe.getViewer().getView().addCanvas3D(offScreenCanvas3D);
	}

	public BufferedImage getImage() {
		return offScreenCanvas3D.doRender(canvas3D.getWidth(), canvas3D.getHeight());
	}

	public double getCoordinate(int value) {
		return value * 1. / 5.;
	}

	public TransformGroup createFloor() {
		TransformGroup floor = Util3D.createTransformGroup(new Vector3d(0, -1, 0), null, 0, 0, 0);
		floor.addChild(new Box(5, .5f, 5, Util3D.createAppearance(Color.GRAY)));
		return floor;
	}

	public static TransformGroup creatLight() {
		TransformGroup tg = new TransformGroup();
		AmbientLight al = new AmbientLight(new Color3f(Color.WHITE));
		al.setInfluencingBounds(new BoundingSphere());
		tg.addChild(al);

		PointLight pl = new PointLight(new Color3f(Color.GRAY), new Point3f(0, 2, 0), new Point3f(.1f, .1f, .1f));
		pl.setInfluencingBounds(new BoundingSphere(new Point3d(), 200));
		tg.addChild(pl);

		PointLight pl2 = new PointLight(new Color3f(Color.GRAY), new Point3f(2, 0, 0), new Point3f(.1f, .1f, .1f));
		pl2.setInfluencingBounds(new BoundingSphere(new Point3d(), 200));
		tg.addChild(pl2);

		PointLight pl3 = new PointLight(new Color3f(Color.GRAY), new Point3f(0, 0, 2), new Point3f(.1f, .1f, .1f));
		pl3.setInfluencingBounds(new BoundingSphere(new Point3d(), 200));
		tg.addChild(pl3);

		DirectionalLight dl = new DirectionalLight(new Color3f(Color.GRAY), new Vector3f(5, -6, -10));
		dl.setInfluencingBounds(new BoundingSphere(new Point3d(0, 0, 0), 1000));
		tg.addChild(dl);

		return tg;
	}

	@Override
	public void endSimulation() {
		viewUpdater.changeToStopConfiguration();
		super.endSimulation();
	}

	@Override
	public void start() {
		super.start();
		viewUpdater.updateStatus(Translate.get("LOG_SIMULATIONINIT"));
	}

	@Override
	public void stop() {
		super.stop();
		viewUpdater.updateStatus(Translate.get("LOG_SIMULATIONSTOP"));
	}

	@Override
	public Agent createAgent() {
		return new Agent3D();
	}

	@Override
	public Cell createCell() {
		return new Cell3D();
	}

	@Override
	public void updateIterations() {
		super.updateIterations();
		viewUpdater.updateIterationsStatus(getIterations());
	}

	@Override
	public void updateCell(Cell cell, Rule rule) {
		super.updateCell(cell, rule);
		Cell3D cell3D = (Cell3D) cell;
		cell3D.updateColor();
	}

	@Override
	public void moveRandomly(Agent agent) {
		super.moveRandomly(agent);
		Agent3D agent3D = (Agent3D) agent;
		Transform3D t3D = Util3D.createTransform3D(new Vector3d(getCoordinate(agent.getX()), getCoordinate(agent.getZ()), getCoordinate(agent.getY())), null, 0, 0, 0);
		agent3D.getTransformGroup().setTransform(t3D);
	}

}
