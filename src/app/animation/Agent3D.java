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
 */

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
