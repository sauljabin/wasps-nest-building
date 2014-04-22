package app.animation;

import java.awt.Color;

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
		tg.addChild(new Sphere(.1f, Util3D.createAppearance(Color.BLACK)));
	}

	public TransformGroup getTransformGroup() {
		return tg;
	}

}
