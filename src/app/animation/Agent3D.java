/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Sphere;

import app.simulation.Agent;
import app.util.Util3D;

public class Agent3D extends Agent {

	private TransformGroup tg;

	public Agent3D() {
		tg = Util3D.createTransformGroup(new Vector3d(0, 0, 0), null, 0, 0, 0);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// tg.addChild(new Sphere(.1f, Util3D.createAppearance(Color.BLACK))); 
		tg.addChild(new Sphere(.1f, Util3D.createTransparencyAppearance(1f)));
	}

	public TransformGroup getTransformGroup() {
		return tg;
	}

}
