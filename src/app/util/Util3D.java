package app.util;

import java.awt.Color;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

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

	public static TransformGroup creatLight() {
		TransformGroup tg = new TransformGroup();
		AmbientLight al = new AmbientLight(new Color3f(Color.WHITE));
		al.setInfluencingBounds(new BoundingSphere());
		tg.addChild(al);

		PointLight pl = new PointLight(new Color3f(Color.GRAY), new Point3f(0, 2, 0), new Point3f(.1f, .1f, .1f));
		pl.setInfluencingBounds(new BoundingSphere(new Point3d(), 200));
		tg.addChild(pl);

		PointLight pl2 = new PointLight(new Color3f(Color.GRAY), new Point3f(2, 0, 0), new Point3f(.1f, .1f, .1f));
		pl2.setInfluencingBounds(new BoundingSphere(new Point3d(), 200));
		tg.addChild(pl2);

		PointLight pl3 = new PointLight(new Color3f(Color.GRAY), new Point3f(0, 0, 2), new Point3f(.1f, .1f, .1f));
		pl3.setInfluencingBounds(new BoundingSphere(new Point3d(), 200));
		tg.addChild(pl3);

		return tg;
	}

	public static Background createBackground(Color color) {
		Background b = new Background(new Color3f(color));
		b.setApplicationBounds(new BoundingSphere());
		return b;
	}
}
