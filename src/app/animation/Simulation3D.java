package app.animation;

import java.awt.Color;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import app.simulation.Agent;
import app.simulation.Cell;
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
	private TransformGroup transformGroup;

	public Simulation3D(Canvas3D canvas3d, int tmax, int m, int latticeSize, Cell initialCell, Rule[] rules, int delay) {
		super(tmax, m, latticeSize, initialCell, rules, delay);
		this.canvas3D = canvas3d;

		universe = new SimpleUniverse(canvas3D);
		universe.getViewingPlatform().setNominalViewingTransform();
		branchGroup = new BranchGroup();
		branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		transformGroup = new TransformGroup();
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		MouseRotate mouseRotate = new MouseRotate();
		mouseRotate.setTransformGroup(transformGroup);
		mouseRotate.setSchedulingBounds(new BoundingSphere());

		MouseWheelZoom mouseWheelZoom = new MouseWheelZoom();
		mouseWheelZoom.setTransformGroup(transformGroup);
		mouseWheelZoom.setSchedulingBounds(new BoundingSphere());

		branchGroup.addChild(mouseRotate);
		branchGroup.addChild(mouseWheelZoom);
		branchGroup.addChild(transformGroup);

		init();
	}

	private void init() {
		// transformGroup.addChild(Util3D.creatLight());

		for (Agent agent : getAgents()) {
			Agent3D agent3D = (Agent3D) agent;
			transformGroup.addChild(agent3D.getTransformGroup());
		}

		for (int x = 0; x < getLatticeSize(); x++) {
			for (int y = 0; y < getLatticeSize(); y++) {
				for (int z = 0; z < getLatticeSize(); z++) {
					Cell3D cell3D = (Cell3D) getLattice()[x][y][z];
					Transform3D t3D = Util3D.createTransform3D(new Vector3d(getCoordinate(x), getCoordinate(z), getCoordinate(y)), null, 0, 0, 0);
					cell3D.getTransformGroup().setTransform(t3D);
					transformGroup.addChild(cell3D.getTransformGroup());
					cell3D.updateColor();
				}
			}
		}

		transformGroup.addChild(Util3D.createBackground(new Color(0, 204, 255)));
		universe.addBranchGraph(branchGroup);
	}

	public double getCoordinate(int value) {
		return value * 1. / 5.;
	}

	public void detach() {
		branchGroup.detach();
	}

	public TransformGroup createFloor() {
		TransformGroup floor = Util3D.createTransformGroup(new Vector3d(0, -1, 0), null, 0, 0, 0);
		floor.addChild(new Box(5, .5f, 5, Util3D.createAppearance(Color.GRAY)));
		return floor;
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
