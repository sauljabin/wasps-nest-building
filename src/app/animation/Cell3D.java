/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;

import app.Config;
import app.simulation.Cell;
import app.util.Util3D;

public class Cell3D extends Cell {

	private TransformGroup tg;
	private Box box;

	public Cell3D() {
		tg = Util3D.createTransformGroup(new Vector3d(0, 0, 0), null, 0, 0, 0);
		box = new Box(.1f, .1f, .1f, Box.ENABLE_APPEARANCE_MODIFY, Util3D.createTransparencyAppearance(Float.parseFloat(Config.get("TRANSPARENCY"))));
		tg.addChild(box);
	}

	public TransformGroup getTransformGroup() {
		return tg;
	}

	public void updateColor() {
		if (getColor() != null) {
			box.getAppearance().setTransparencyAttributes(null);
			ColoringAttributes ca = new ColoringAttributes();
			ca.setColor(new Color3f(getColor()));
			box.getAppearance().setColoringAttributes(ca);
		}
	}

}
