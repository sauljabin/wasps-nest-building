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
 */

package app.animation;

import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;

public class OffScreenCanvas3D extends Canvas3D {

	private static final long serialVersionUID = 1231874944560674943L;

	public OffScreenCanvas3D(GraphicsConfiguration config) {
		super(config, true);
	}

	public BufferedImage doRender(int width, int height) {
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		ImageComponent2D buffer = new ImageComponent2D(ImageComponent.FORMAT_RGB, bImage);
		setOffScreenBuffer(buffer);
		renderOffScreenBuffer();
		waitForOffScreenRendering();
		bImage = getOffScreenBuffer().getImage();
		
		bImage.flush();
		return bImage;
	}

}
