/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of WaspsNestBuilding.
 * <p>
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import org.scijava.java3d.*;
import org.scijava.vecmath.Color3f;
import org.scijava.vecmath.Vector3d;

import java.awt.*;

public class Util3D {

    public static double ROT_DEG_45 = Math.PI / 4;
    public static double ROT_DEG_90 = Math.PI / 2;
    public static double ROT_DEG_135 = ROT_DEG_90 + ROT_DEG_45;
    public static double ROT_DEG_180 = Math.PI;
    public static double ROT_DEG_225 = ROT_DEG_180 + ROT_DEG_45;
    public static double ROT_DEG_270 = ROT_DEG_225 + ROT_DEG_45;

    public static Appearance createAppearance(Color color) {
        Appearance appearance = new Appearance();
        Material material = new Material();
        Color3f color3f = new Color3f(color);
        material.setAmbientColor(color3f);
        material.setDiffuseColor(color3f);
        material.setSpecularColor(1, 1, 1);
        appearance.setMaterial(material);
        return appearance;
    }

    public static Appearance createTransparencyAppearance(float t) {
        Appearance ap = new Appearance();
        TransparencyAttributes ta = new TransparencyAttributes();
        ta.setTransparencyMode(TransparencyAttributes.BLENDED);
        ta.setTransparency(t);

        ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
        ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        ap.setCapability(Appearance.ALLOW_MATERIAL_READ);
        ap.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
        ap.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ);
        ap.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE);

        ap.setTransparencyAttributes(ta);
        return ap;
    }

    public static Transform3D createTransform3D(Vector3d displace, Vector3d scale, double rotationX, double rotationY, double rotationZ) {
        Transform3D t = new Transform3D();
        Transform3D taux = new Transform3D();

        if (scale != null) {
            taux.setScale(scale);
            t.mul(taux);
        }

        if (displace != null) {
            taux.setTranslation(displace);
            t.mul(taux);
        }

        taux.rotX(rotationX);
        t.mul(taux);

        taux.rotY(rotationY);
        t.mul(taux);

        taux.rotZ(rotationZ);
        t.mul(taux);

        return t;
    }

    public static TransformGroup createTransformGroup(Vector3d displace, Vector3d scale, double rotationX, double rotationY, double rotationZ) {
        TransformGroup tg = new TransformGroup();
        tg.setTransform(createTransform3D(displace, scale, rotationX, rotationY, rotationZ));
        return tg;
    }

    public static Background createBackground(Color color) {
        Background b = new Background(new Color3f(color));
        b.setApplicationBounds(new BoundingSphere());
        return b;
    }
}
