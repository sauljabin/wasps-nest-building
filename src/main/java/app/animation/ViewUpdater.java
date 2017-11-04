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

public interface ViewUpdater {
    public void updateStatus(String status);

    public void updateIterationsStatus(int iterations);

    public Canvas3D getCanvas3D();

    public void changeToStopConfiguration();
}
