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

package app.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Canvas3D;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.Config;
import app.Log;
import app.Translate;
import app.animation.Simulation3D;
import app.animation.ViewUpdater;
import app.simulation.Cell;
import app.simulation.Configuration;
import app.simulation.Exporter;
import app.simulation.Importer;
import app.simulation.Rule;
import app.util.UtilDate;
import app.util.UtilFile;
import app.util.UtilImage;

public class ControllerViewApp extends Controller implements ViewUpdater {

	private ViewApp viewApp;
	private SpinnerNumberModel spnDelayModel;
	private SpinnerNumberModel spnIterationsModel;
	private SpinnerNumberModel spnAgentsModel;
	private SpinnerNumberModel spnCellsPerAxisModel;
	private SpinnerNumberModel spnBlockXModel;
	private SpinnerNumberModel spnBlockYModel;
	private SpinnerNumberModel spnBlockZModel;
	private SpinnerNumberModel spnStateModel;
	private List<Rule> rules;
	private boolean simulationStarted;
	private Simulation3D simulation;

	public ControllerViewApp() {
		viewApp = new ViewApp();
		initView();
		viewApp.setController(this);

		if (Boolean.parseBoolean(Config.get("INIT_MAXIMIZED")))
			viewApp.setExtendedState(JFrame.MAXIMIZED_BOTH);

		viewApp.setVisible(true);
		rules = new ArrayList<Rule>();
		Log.setLogTextArea(viewApp.getConsole());
		Log.info(getClass(), Translate.get("LOG_APPINIT"));
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

		viewApp.getBtnSelectColor().setColor(Color.BLACK);

		viewApp.getBtnStop().setEnabled(false);
		viewApp.getBtnStart().setEnabled(true);
		viewApp.getBtnSelectColor().setEnabled(true);
		viewApp.getMenuItemExportConfig().setEnabled(true);
		viewApp.getMenuItemImportConfig().setEnabled(true);
		viewApp.getMenuItemClear().setEnabled(true);
		viewApp.getSpnAgents().setEnabled(true);
		viewApp.getSpnBlockX().setEnabled(true);
		viewApp.getSpnBlockY().setEnabled(true);
		viewApp.getSpnBlockZ().setEnabled(true);
		viewApp.getSpnCellsPerAxis().setEnabled(true);
		viewApp.getSpnDelay().setEnabled(true);
		viewApp.getSpnIterations().setEnabled(true);
		viewApp.getSpnState().setEnabled(true);
		viewApp.getBtnZoomIn().setEnabled(false);
		viewApp.getBtnZoomOut().setEnabled(false);
		viewApp.getBtnSaveImage().setEnabled(false);
		viewApp.getBtnArrowDown().setEnabled(false);
		viewApp.getBtnArrowLeft().setEnabled(false);
		viewApp.getBtnArrowRight().setEnabled(false);
		viewApp.getBtnArrowUp().setEnabled(false);

		viewApp.getTxtDescrip().setText(UtilDate.nowString());

		updateStatus(Translate.get("LOG_SIMULATIONNOTINIT"));
		viewApp.getLblIterationsStatus().setText("");
	}

	public void stop() {
		viewApp.getBtnStop().setEnabled(false);
		viewApp.getBtnStart().setEnabled(true);
		viewApp.getSpnDelay().setEnabled(true);
		viewApp.getSpnIterations().setEnabled(true);
		viewApp.getMenuItemExportConfig().setEnabled(true);
		viewApp.getMenuItemClear().setEnabled(true);

		Log.info(getClass(), Translate.get("LOG_SIMULATIONSTOP"));

		if (simulation != null)
			simulation.stop();

	}

	public void start() {
		viewApp.getBtnStop().setEnabled(true);
		viewApp.getBtnStart().setEnabled(false);
		viewApp.getBtnSelectColor().setEnabled(false);
		viewApp.getMenuItemImportConfig().setEnabled(false);
		viewApp.getSpnAgents().setEnabled(false);
		viewApp.getSpnBlockX().setEnabled(false);
		viewApp.getSpnBlockY().setEnabled(false);
		viewApp.getSpnBlockZ().setEnabled(false);
		viewApp.getSpnCellsPerAxis().setEnabled(false);
		viewApp.getSpnState().setEnabled(false);
		viewApp.getBtnZoomIn().setEnabled(true);
		viewApp.getBtnZoomOut().setEnabled(true);
		viewApp.getBtnSaveImage().setEnabled(true);
		viewApp.getBtnArrowDown().setEnabled(true);
		viewApp.getBtnArrowLeft().setEnabled(true);
		viewApp.getBtnArrowRight().setEnabled(true);
		viewApp.getBtnArrowUp().setEnabled(true);
		Log.info(getClass(), Translate.get("LOG_SIMULATIONINIT"));
		simulationStarted = true;

		if (simulation == null) {
			Cell initialCell = new Cell((Integer) spnBlockXModel.getValue(), (Integer) spnBlockYModel.getValue(), (Integer) spnBlockZModel.getValue(), (Integer) spnStateModel.getValue(), viewApp.getBtnSelectColor().getColor());
			simulation = new Simulation3D(this, (Integer) spnIterationsModel.getValue(), (Integer) spnAgentsModel.getValue(), (Integer) spnCellsPerAxisModel.getValue(), initialCell, rules.toArray(new Rule[rules.size()]), (Integer) spnDelayModel.getValue());
		}
		simulation.start();

	}

	@Override
	public void updateIterationsStatus(int iterations) {
		viewApp.getLblIterationsStatus().setText(String.format("%s %d", Translate.get("GUI_ITERATIONS"), iterations));
	}

	@Override
	public void updateStatus(String status) {
		viewApp.getLblStatus().setText(status);
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
		else if (source.equals(viewApp.getBtnStart()))
			start();
		else if (source.equals(viewApp.getBtnStop()))
			stop();
		else if (source.equals(viewApp.getBtnZoomIn()))
			zoomIn();
		else if (source.equals(viewApp.getBtnZoomOut()))
			zoomOut();
		else if (source.equals(viewApp.getMenuItemShowRules()))
			showRules();
		else if (source.equals(viewApp.getMenuItemImportConfig()))
			importConfig();
		else if (source.equals(viewApp.getMenuItemExportConfig()))
			exportConfig();
		else if (source.equals(viewApp.getMenuItemClear()))
			clear();
		else if (source.equals(viewApp.getBtnSaveImage()))
			saveImage();
		else if (source.equals(viewApp.getSpnDelay()))
			changeDelay();
		else if (source.equals(viewApp.getSpnIterations()))
			changeIterations();
	}

	public void changeIterations() {
		if (simulation == null)
			return;
		simulation.setTmax((Integer) spnIterationsModel.getValue());
	}

	public void changeDelay() {
		if (simulation == null)
			return;
		simulation.setDelay((Integer) spnDelayModel.getValue());
	}

	public void saveImage() {
		BufferedImage bImage = simulation.getImage();

		String ext = "png";

		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File("."));
		file.setSelectedFile(new File(viewApp.getTxtDescrip().getText()));
		file.setAcceptAllFileFilterUsed(false);
		file.setMultiSelectionEnabled(false);
		file.setFileFilter(new FileNameExtensionFilter(ext.toUpperCase(), ext));
		file.showSaveDialog(viewApp);
		File path = file.getSelectedFile();

		if (path == null)
			return;

		String fileName = path.getAbsolutePath();

		if (!UtilFile.isFileType(fileName, ext))
			fileName += "." + ext;

		try {
			UtilImage.writeImage(bImage, fileName);
			Log.info(getClass(), Translate.get("LOG_SAVEIMAGE") + " " + fileName);
		} catch (Exception e) {
			Log.error(getClass(), Translate.get("LOG_ERRORSAVEIMAGE"), e);
			return;
		}

	}

	public void clear() {
		initView();
		rules.clear();
		simulationStarted = false;
		if (simulation != null)
			simulation.detach();
		simulation = null;
	}

	public void exportConfig() {

		String ext = "xml";

		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File("."));
		file.setSelectedFile(new File(viewApp.getTxtDescrip().getText()));
		file.setAcceptAllFileFilterUsed(false);
		file.setMultiSelectionEnabled(false);
		file.setFileFilter(new FileNameExtensionFilter(ext.toUpperCase(), ext));
		file.showSaveDialog(viewApp);
		File path = file.getSelectedFile();

		if (path == null)
			return;

		Configuration configuration = new Configuration((Integer) spnDelayModel.getValue(), (Integer) spnIterationsModel.getValue(), (Integer) spnAgentsModel.getValue(), (Integer) spnCellsPerAxisModel.getValue(), viewApp.getTxtDescrip().getText());
		Cell initialCell = new Cell((Integer) spnBlockXModel.getValue(), (Integer) spnBlockYModel.getValue(), (Integer) spnBlockZModel.getValue(), (Integer) spnStateModel.getValue(), viewApp.getBtnSelectColor().getColor());

		String fileName = path.getAbsolutePath();

		if (!UtilFile.isFileType(fileName, ext))
			fileName += "." + ext;

		Exporter exporter = new Exporter(fileName, configuration, initialCell, rules);
		try {
			exporter.exportXML();
			Log.info(getClass(), Translate.get("LOG_EXPORTSUCCESSFUL") + " " + fileName);
		} catch (Exception e) {
			Log.error(getClass(), Translate.get("LOG_EXPORTERROR"), e);
			return;
		}
	}

	public void importConfig() {
		JFileChooser file = new JFileChooser(new File("."));
		file.setAcceptAllFileFilterUsed(false);
		file.setMultiSelectionEnabled(false);
		file.setFileFilter(new FileNameExtensionFilter("XML", "xml"));
		file.showOpenDialog(viewApp);
		File path = file.getSelectedFile();

		if (path == null)
			return;

		Importer importer = new Importer(path.getAbsolutePath());
		try {
			importer.importXML();
			Log.info(getClass(), Translate.get("LOG_IMPORTSUCCESSFUL"));
		} catch (Exception e) {
			Log.error(getClass(), Translate.get("LOG_IMPORTERROR"), e);
			return;
		}

		viewApp.getTxtDescrip().setText(importer.getConfiguration().getDescription());
		viewApp.getSpnDelay().setValue(new Integer(importer.getConfiguration().getDelay()));
		viewApp.getSpnAgents().setValue(new Integer(importer.getConfiguration().getAgents()));
		viewApp.getSpnIterations().setValue(new Integer(importer.getConfiguration().getIterations()));
		viewApp.getSpnCellsPerAxis().setValue(new Integer(importer.getConfiguration().getLatticeSize()));
		viewApp.getSpnBlockX().setValue(new Integer(importer.getInitialCell().getX()));
		viewApp.getSpnBlockY().setValue(new Integer(importer.getInitialCell().getY()));
		viewApp.getSpnBlockZ().setValue(new Integer(importer.getInitialCell().getZ()));
		viewApp.getSpnState().setValue(new Integer(importer.getInitialCell().getState()));
		viewApp.getBtnSelectColor().setColor(importer.getInitialCell().getColor());

		rules = importer.getRules();
	}

	public void showRules() {
		new ControllerViewRules(rules, simulationStarted);
	}

	public void zoomOut() {

	}

	public void zoomIn() {

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

	@Override
	public Canvas3D getCanvas3D() {
		return viewApp.getCanvas3D();
	}

	@Override
	public void changeToStopConfiguration() {
		viewApp.getBtnStop().setEnabled(false);
		viewApp.getBtnStart().setEnabled(true);
		viewApp.getSpnDelay().setEnabled(true);
		viewApp.getSpnIterations().setEnabled(true);
		viewApp.getMenuItemExportConfig().setEnabled(true);
		viewApp.getMenuItemClear().setEnabled(true);

		Log.info(getClass(), Translate.get("LOG_SIMULATIONSTOP"));
	}

}
