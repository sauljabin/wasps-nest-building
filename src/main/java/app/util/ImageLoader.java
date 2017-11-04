/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of WaspsNestBuilding.
 * <p>
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import javax.swing.*;

public class ImageLoader {

    public static ImageIcon loadImage(String name) {
        return new ImageIcon(ClassLoader.getSystemResource("images/" + name + ".png"));
    }
}
