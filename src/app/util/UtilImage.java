/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
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
