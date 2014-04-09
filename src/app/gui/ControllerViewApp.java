package app.gui;

import java.awt.Color;
import java.awt.Font;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JColorChooser;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

import app.Config;
import app.Log;
import app.Translate;

public class ControllerViewApp extends Controller {

	private ViewApp viewApp;
	private SpinnerNumberModel spnDelayModel;
	private SpinnerNumberModel spnIterationsModel;
	private SpinnerNumberModel spnAgentsModel;
	private SpinnerNumberModel spnCellsPerAxisModel;
	private SpinnerNumberModel spnBlockXModel;
	private SpinnerNumberModel spnBlockYModel;
	private SpinnerNumberModel spnBlockZModel;
	private SpinnerNumberModel spnStateModel;
	private int iterations;
	private SimpleUniverse universe;

	public ControllerViewApp() {
		viewApp = new ViewApp();
		initView();
		viewApp.setController(this);
		// viewApp.setExtendedState(JFrame.MAXIMIZED_BOTH);
		viewApp.setVisible(true);
	}

	public void initView() {
		spnDelayModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_DELAY")), 0, Integer.parseInt(Config.get("MAX_DELAY")), 1);
		viewApp.getSpnDelay().setModel(spnDelayModel);

		spnIterationsModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_ITERATIONS")), 0, Integer.parseInt(Config.get("MAX_ITERATIONS")), 1);
		viewApp.getSpnIterations().setModel(spnIterationsModel);

		spnAgentsModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_AGENTS")), 0, Integer.parseInt(Config.get("MAX_AGENTS")), 1);
		viewApp.getSpnAgents().setModel(spnAgentsModel);

		spnCellsPerAxisModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_CELLSPERAXIS")), 0, Integer.parseInt(Config.get("MAX_CELLSPERAXIS")), 1);
		viewApp.getSpnCellsPerAxis().setModel(spnCellsPerAxisModel);

		spnBlockXModel = new SpinnerNumberModel(spnCellsPerAxisModel.getNumber().intValue() / 2, 0, spnCellsPerAxisModel.getNumber().intValue(), 1);
		viewApp.getSpnBlockX().setModel(spnBlockXModel);

		spnBlockYModel = new SpinnerNumberModel(spnCellsPerAxisModel.getNumber().intValue() / 2, 0, spnCellsPerAxisModel.getNumber().intValue(), 1);
		viewApp.getSpnBlockY().setModel(spnBlockYModel);

		spnBlockZModel = new SpinnerNumberModel(0, 0, spnCellsPerAxisModel.getNumber().intValue(), 1);
		viewApp.getSpnBlockZ().setModel(spnBlockZModel);

		spnStateModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_STATE")), 0, Integer.parseInt(Config.get("MAX_STATE")), 1);
		viewApp.getSpnState().setModel(spnStateModel);

		viewApp.getLblShowColor().setOpaque(true);
		viewApp.getLblShowColor().setBackground(Color.BLACK);

		viewApp.getBtnStop().setEnabled(false);

		universe = new SimpleUniverse(viewApp.getCanvas3D());
		BranchGroup group = new BranchGroup();
		addObjects(group);
		addLights(group);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(group);

		updateStatus();
		Log.setLogTextArea(viewApp.getConsole());
		Log.info(getClass(), Translate.get("LOG_APPINIT"));
	}

	public void stop() {
		viewApp.getBtnStop().setEnabled(false);
		viewApp.getBtnStart().setEnabled(true);
		viewApp.getBtnAddBlock().setEnabled(true);
		viewApp.getBtnSelectColor().setEnabled(true);
		viewApp.getMenuItemExportConfig().setEnabled(true);
		viewApp.getMenuItemImportConfig().setEnabled(true);
		viewApp.getMenuItemClean().setEnabled(true);
		viewApp.getSpnAgents().setEnabled(true);
		viewApp.getSpnBlockX().setEnabled(true);
		viewApp.getSpnBlockY().setEnabled(true);
		viewApp.getSpnBlockZ().setEnabled(true);
		viewApp.getSpnCellsPerAxis().setEnabled(true);
		viewApp.getSpnDelay().setEnabled(true);
		viewApp.getSpnIterations().setEnabled(true);
		viewApp.getSpnState().setEnabled(true);
		Log.info(getClass(), Translate.get("LOG_SIMULATIONSTOP"));
	}

	public void start() {
		viewApp.getBtnStop().setEnabled(true);
		viewApp.getBtnStart().setEnabled(false);
		viewApp.getBtnAddBlock().setEnabled(false);
		viewApp.getBtnSelectColor().setEnabled(false);
		viewApp.getMenuItemExportConfig().setEnabled(false);
		viewApp.getMenuItemImportConfig().setEnabled(false);
		viewApp.getMenuItemClean().setEnabled(false);
		viewApp.getSpnAgents().setEnabled(false);
		viewApp.getSpnBlockX().setEnabled(false);
		viewApp.getSpnBlockY().setEnabled(false);
		viewApp.getSpnBlockZ().setEnabled(false);
		viewApp.getSpnCellsPerAxis().setEnabled(false);
		viewApp.getSpnDelay().setEnabled(false);
		viewApp.getSpnIterations().setEnabled(false);
		viewApp.getSpnState().setEnabled(false);
		Log.info(getClass(), Translate.get("LOG_SIMULATIONINIT"));
	}

	public void updateStatus() {
		viewApp.getLblStatus().setText(String.format("%s: %d", Translate.get("GUI_ITERATIONS"), iterations));
	}

	@Override
	public void action(Object source) {
		if (source.equals(viewApp))
			close();
		else if (source.equals(viewApp.getMenuItemClose()))
			close();
		else if (source.equals(viewApp.getMenuItemAbout()))
			about();
		else if (source.equals(viewApp.getMenuItemShowConfig()))
			showConfig();
		else if (source.equals(viewApp.getSpnCellsPerAxis()))
			changeCellsPerAxis();
		else if (source.equals(viewApp.getBtnSelectColor()))
			selectColor();
		else if (source.equals(viewApp.getBtnStart()))
			start();
		else if (source.equals(viewApp.getBtnStop()))
			stop();
		else if (source.equals(viewApp.getBtnZoomIn()))
			zoomIn();
		else if (source.equals(viewApp.getBtnZoomOut()))
			zoomOut();
		else if (source.equals(viewApp.getBtnLeftCam()))
			leftCam();
		else if (source.equals(viewApp.getBtnRightCam()))
			rightCam();
		else if (source.equals(viewApp.getBtnUpCam()))
			upCam();
		else if (source.equals(viewApp.getBtnDownCam()))
			downCam();
	}

	public void downCam() {

	}

	public void upCam() {
		Transform3D trNew = new Transform3D();
		trNew.rotZ(Math.PI/8);
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(trNew);
	}

	public void rightCam() {

	}

	public void leftCam() {

	}

	public void zoomOut() {

	}

	public void zoomIn() {
		Transform3D trNew = new Transform3D();
		trNew.setTranslation(new Vector3d(3.0, 3.0, 3.0));
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(trNew);

	}

	public void addLights(BranchGroup group) {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);

		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);

		// Set up the ambient light
		Color3f ambientColor = new Color3f(.1f, .1f, .1f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		group.addChild(ambientLightNode);
	}

	private void addObjects(BranchGroup group) {
		Font3D f3d = new Font3D(new Font("TestFont", Font.PLAIN, 2), new FontExtrusion());
		Text3D text = new Text3D(f3d, new String("Java3D.org"), new Point3f(-3.5f, -.5f, -4.5f));

		text.setString("Java3D.org");
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f blue = new Color3f(.2f, 0.2f, 0.6f);
		Appearance a = new Appearance();
		Material m = new Material(blue, blue, blue, white, 80.0f);
		m.setLightingEnable(true);
		a.setMaterial(m);

		Shape3D sh = new Shape3D();
		sh.setGeometry(text);
		sh.setAppearance(a);
		TransformGroup tg = new TransformGroup();
		Transform3D t3d = new Transform3D();
		Transform3D tDown = new Transform3D();
		Transform3D rot = new Transform3D();
		Vector3f v3f = new Vector3f(-1.6f, -1.35f, -15.5f);
		t3d.setTranslation(v3f);
		rot.rotX(Math.PI / 5);
		//t3d.mul(rot);
		v3f = new Vector3f(0, -1.4f, 0f);
		tDown.setTranslation(v3f);
		t3d.mul(tDown);
		tg.setTransform(t3d);
		tg.addChild(sh);
		group.addChild(tg);

	}

	public void selectColor() {
		Color selectedColor = JColorChooser.showDialog(viewApp, Translate.get("GUI_SELECTCOLOR"), viewApp.getLblShowColor().getBackground());
		if (selectedColor != null)
			viewApp.getLblShowColor().setBackground(selectedColor);
	}

	public void changeCellsPerAxis() {
		spnBlockXModel.setMaximum(spnCellsPerAxisModel.getNumber().intValue());
		spnBlockYModel.setMaximum(spnCellsPerAxisModel.getNumber().intValue());
		spnBlockZModel.setMaximum(spnCellsPerAxisModel.getNumber().intValue());

		if (spnBlockXModel.getNumber().intValue() > spnCellsPerAxisModel.getNumber().intValue())
			spnBlockXModel.setValue(spnCellsPerAxisModel.getNumber().intValue());

		if (spnBlockYModel.getNumber().intValue() > spnCellsPerAxisModel.getNumber().intValue())
			spnBlockYModel.setValue(spnCellsPerAxisModel.getNumber().intValue());

		if (spnBlockZModel.getNumber().intValue() > spnCellsPerAxisModel.getNumber().intValue())
			spnBlockZModel.setValue(spnCellsPerAxisModel.getNumber().intValue());
	}

	public void close() {
		System.exit(0);
	}

	public void about() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewAbout();
			}
		});
	}

	public void showConfig() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewConfig();
			}
		});
	}

}
