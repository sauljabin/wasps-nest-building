/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of WaspsNestBuilding.
 * <p>
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

import org.scijava.java3d.Canvas3D;
import org.scijava.java3d.ImageComponent;
import org.scijava.java3d.ImageComponent2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OffScreenCanvas3D extends Canvas3D {

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
        setOffScreenBuffer(null);
        return bImage;
    }

}
