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
 */

package app.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Image Handling
 * 
 * @author Saul Pina - sauljp07@gmail.com
 * @version 1.0.0
 */
public class UtilImage {

	public static boolean writeImage(BufferedImage image, String path) throws IOException {
		return writeImage(image, new File(path));
	}

	public static boolean writeImage(BufferedImage image, File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
		return ImageIO.write(image, extension, file);
	}
}
