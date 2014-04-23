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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Canvas3D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sun.j3d.utils.universe.SimpleUniverse;

import net.miginfocom.swing.MigLayout;
import app.Translate;
import app.util.JColorChooserButton;

public class ViewApp extends View {

	private static final long serialVersionUID = 7096335230345597673L;
	private JMenuBar menuBar;
	private JMenu menuOptions;
	private JMenuItem menuItemShowConfig;
	private JMenuItem menuItemClose;
	private JMenu menuHelp;
	private JMenuItem menuItemAbout;
	private JMenuItem menuItemShowRules;
	private JMenuItem menuItemImportConfig;
	private JMenuItem menuItemExportConfig;
	private JPanel pnlSouth;
	private JTextArea console;
	private JPanel pnlWest;
	private JPanel pnlConfig;
	private TitledBorder pnlConfigBorder;
	private JLabel lblIterations;
	private JSpinner spnIterations;
	private JLabel lblDelay;
	private JSpinner spnDelay;
	private JLabel lblAgents;
	private JSpinner spnAgents;
	private JLabel lblCellsPerAxis;
	private JSpinner spnCellsPerAxis;
	private JPanel pnlBlock;
	private TitledBorder pnlBlockBorder;
	private JLabel lblBlockX;
	private JSpinner spnBlockX;
	private JLabel lblBlockY;
	private JSpinner spnBlockY;
	private JLabel lblBlockZ;
	private JSpinner spnBlockZ;
	private JLabel lblState;
	private JSpinner spnState;
	private JLabel lblColor;
	private JColorChooserButton btnSelectColor;
	private JMenuItem menuItemClear;
	private JButton btnStart;
	private JButton btnStop;
	private JPanel pnlCenter;
	private JPanel pnlStatus;
	private JButton btnZoomIn;
	private JButton btnZoomOut;
	private JLabel lblStatus;
	private Canvas3D canvas3D;
	private JLabel lblDescrip;
	private JTextField txtDescrip;
	private JPanel pnlCanvas;
	private JButton btnSaveImage;
	private JPanel pnlEast;
	private JButton btnArrowUp;
	private JButton btnArrowDown;
	private JButton btnArrowLeft;
	private JButton btnArrowRight;
	private JLabel lblIterationsStatus;

	@Override
	public void init() {
		setSize(800, 600);
		setLayout(new BorderLayout());

		// MENU
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuOptions = new JMenu();
		menuHelp = new JMenu();
		menuBar.add(menuOptions);
		menuBar.add(menuHelp);
		menuItemShowConfig = new JMenuItem();
		menuItemShowRules = new JMenuItem();
		menuItemClear = new JMenuItem();
		menuItemImportConfig = new JMenuItem();
		menuItemExportConfig = new JMenuItem();
		menuItemClose = new JMenuItem();
		menuOptions.add(menuItemShowConfig);
		menuOptions.add(menuItemShowRules);
		menuOptions.add(new JSeparator());
		menuOptions.add(menuItemImportConfig);
		menuOptions.add(menuItemExportConfig);
		menuOptions.add(menuItemClear);
		menuOptions.add(new JSeparator());
		menuOptions.add(menuItemClose);
		menuItemAbout = new JMenuItem();
		menuHelp.add(menuItemAbout);
		// MENU

		// SOUTH
		pnlSouth = new JPanel();
		pnlSouth.setLayout(new MigLayout());
		JScrollPane scrollPanelConsole = new JScrollPane();
		pnlSouth.add(scrollPanelConsole, "width 100%, height 100");
		console = new JTextArea();
		scrollPanelConsole.setViewportView(console);
		add(pnlSouth, BorderLayout.SOUTH);
		// SOUTH

		// WEST
		pnlWest = new JPanel(new MigLayout());
		pnlConfig = new JPanel(new MigLayout());
		pnlConfigBorder = BorderFactory.createTitledBorder("");
		pnlConfig.setBorder(pnlConfigBorder);

		lblDelay = new JLabel();
		spnDelay = new JSpinner();

		lblIterations = new JLabel();
		spnIterations = new JSpinner();

		lblAgents = new JLabel();
		spnAgents = new JSpinner();

		lblCellsPerAxis = new JLabel();
		spnCellsPerAxis = new JSpinner();

		pnlConfig.add(lblDelay, "width 80");
		pnlConfig.add(spnDelay, "width 100, wrap");
		pnlConfig.add(lblIterations, "grow");
		pnlConfig.add(spnIterations, "grow, wrap");
		pnlConfig.add(lblAgents, "grow");
		pnlConfig.add(spnAgents, "grow, wrap");
		pnlConfig.add(lblCellsPerAxis, "grow");
		pnlConfig.add(spnCellsPerAxis, "grow, wrap");

		pnlBlock = new JPanel(new MigLayout());
		pnlBlockBorder = BorderFactory.createTitledBorder("");
		pnlBlock.setBorder(pnlBlockBorder);

		lblBlockX = new JLabel();
		spnBlockX = new JSpinner();

		lblBlockY = new JLabel();
		spnBlockY = new JSpinner();

		lblBlockZ = new JLabel();
		spnBlockZ = new JSpinner();

		lblState = new JLabel();
		spnState = new JSpinner();

		lblColor = new JLabel();
		btnSelectColor = new JColorChooserButton();

		pnlBlock.add(lblBlockX, "width 80");
		pnlBlock.add(spnBlockX, "width 100, wrap");
		pnlBlock.add(lblBlockY, "grow");
		pnlBlock.add(spnBlockY, "grow, wrap");
		pnlBlock.add(lblBlockZ, "grow");
		pnlBlock.add(spnBlockZ, "grow, wrap");
		pnlBlock.add(lblState, "grow");
		pnlBlock.add(spnState, "grow, wrap");
		pnlBlock.add(lblColor, "grow");
		pnlBlock.add(btnSelectColor, "grow, height 30, wrap");

		pnlWest.add(pnlConfig, "width 210, wrap");
		pnlWest.add(pnlBlock, "width 210, wrap 20");

		add(pnlWest, BorderLayout.WEST);
		// WEST

		// EAST
		pnlEast = new JPanel(new MigLayout());

		btnStart = new JButton(new ImageIcon("img/play20px.png"));
		btnStop = new JButton(new ImageIcon("img/pause20px.png"));

		btnZoomIn = new JButton(new ImageIcon("img/zoom-in20px.png"));
		btnZoomOut = new JButton(new ImageIcon("img/zoom-out20px.png"));
		btnSaveImage = new JButton(new ImageIcon("img/camera20px.png"));

		btnArrowUp = new JButton(new ImageIcon("img/arrow-up20px.png"));
		btnArrowDown = new JButton(new ImageIcon("img/arrow-down20px.png"));
		btnArrowLeft = new JButton(new ImageIcon("img/arrow-left20px.png"));
		btnArrowRight = new JButton(new ImageIcon("img/arrow-right20px.png"));

		pnlEast.add(btnStart, "wrap");
		pnlEast.add(btnStop, "wrap 20");
		pnlEast.add(btnSaveImage, "wrap");
		// pnlEast.add(btnArrowUp, "wrap");
		// pnlEast.add(btnArrowDown, "wrap");
		// pnlEast.add(btnArrowRight, "wrap");
		// pnlEast.add(btnArrowLeft, "wrap");
		// pnlEast.add(btnZoomIn, "wrap");
		// pnlEast.add(btnZoomOut, "wrap");

		add(pnlEast, BorderLayout.EAST);
		// EAST

		// CENTER
		pnlCenter = new JPanel(new BorderLayout());

		pnlStatus = new JPanel(new MigLayout());
		lblDescrip = new JLabel();
		txtDescrip = new JTextField();
		lblStatus = new JLabel();
		lblStatus.setForeground(Color.RED);
		lblIterationsStatus = new JLabel();

		pnlStatus.add(lblDescrip, "gapright 60");
		pnlStatus.add(txtDescrip, "width 100%, wrap, span 2");
		pnlStatus.add(lblIterationsStatus);
		pnlStatus.add(lblStatus);

		pnlCenter.add(pnlStatus, BorderLayout.SOUTH);

		// CANVAS 3D
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas3D = new Canvas3D(config);
		pnlCanvas = new JPanel(new BorderLayout());
		pnlCanvas.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 7));
		pnlCanvas.add(canvas3D, BorderLayout.CENTER);
		pnlCenter.add(pnlCanvas, BorderLayout.CENTER);
		// CANVAS 3D

		add(pnlCenter, BorderLayout.CENTER);
		// CENTER

		addAction();
	}

	public void addAction() {
		addMenuItemToAction(menuItemAbout);
		addMenuItemToAction(menuItemClose);
		addMenuItemToAction(menuItemExportConfig);
		addMenuItemToAction(menuItemImportConfig);
		addMenuItemToAction(menuItemShowConfig);
		addMenuItemToAction(menuItemShowRules);
		addMenuItemToAction(menuItemClear);

		addButtonToAction(btnSelectColor);
		addButtonToAction(btnStart);
		addButtonToAction(btnStop);
		addButtonToAction(btnZoomIn);
		addButtonToAction(btnZoomOut);
		addButtonToAction(btnSaveImage);

		addSpinnerToAction(spnAgents);
		addSpinnerToAction(spnBlockX);
		addSpinnerToAction(spnBlockY);
		addSpinnerToAction(spnBlockZ);
		addSpinnerToAction(spnCellsPerAxis);
		addSpinnerToAction(spnDelay);
		addSpinnerToAction(spnIterations);
		addSpinnerToAction(spnState);
	}

	@Override
	public void translate() {
		menuOptions.setText(Translate.get("GUI_OPTIONS"));
		menuHelp.setText(Translate.get("GUI_HELP"));
		menuItemShowConfig.setText(Translate.get("GUI_SHOWCONFIG"));
		menuItemClose.setText(Translate.get("GUI_CLOSE"));
		menuItemAbout.setText(Translate.get("GUI_ABOUT"));
		menuItemShowRules.setText(Translate.get("GUI_SHOWRULES"));
		menuItemClear.setText(Translate.get("GUI_CLEAN"));
		menuItemExportConfig.setText(Translate.get("GUI_EXPORTCONFIG"));
		menuItemImportConfig.setText(Translate.get("GUI_IMPORTCONFIG"));
		pnlConfigBorder.setTitle(Translate.get("GUI_CONFIG"));
		pnlBlockBorder.setTitle(Translate.get("GUI_INITIALBLOCK"));
		lblIterations.setText(Translate.get("GUI_ITERATIONS"));
		lblDelay.setText(Translate.get("GUI_DELAY"));
		lblAgents.setText(Translate.get("GUI_AGENTS"));
		lblCellsPerAxis.setText(Translate.get("GUI_CELLSPERAXIS"));
		lblBlockX.setText(Translate.get("GUI_X"));
		lblBlockY.setText(Translate.get("GUI_Y"));
		lblBlockZ.setText(Translate.get("GUI_Z"));
		lblState.setText(Translate.get("GUI_STATE"));
		lblColor.setText(Translate.get("GUI_COLOR"));
		lblDescrip.setText(Translate.get("GUI_DESCRIP"));
	}

	public JMenuItem getMenuItemShowConfig() {
		return menuItemShowConfig;
	}

	public JMenuItem getMenuItemClose() {
		return menuItemClose;
	}

	public JMenuItem getMenuItemAbout() {
		return menuItemAbout;
	}

	public JMenuItem getMenuItemShowRules() {
		return menuItemShowRules;
	}

	public JMenuItem getMenuItemImportConfig() {
		return menuItemImportConfig;
	}

	public JMenuItem getMenuItemExportConfig() {
		return menuItemExportConfig;
	}

	public JTextArea getConsole() {
		return console;
	}

	public JSpinner getSpnIterations() {
		return spnIterations;
	}

	public JSpinner getSpnDelay() {
		return spnDelay;
	}

	public JSpinner getSpnAgents() {
		return spnAgents;
	}

	public JSpinner getSpnCellsPerAxis() {
		return spnCellsPerAxis;
	}

	public JSpinner getSpnBlockX() {
		return spnBlockX;
	}

	public JSpinner getSpnBlockY() {
		return spnBlockY;
	}

	public JSpinner getSpnBlockZ() {
		return spnBlockZ;
	}

	public JSpinner getSpnState() {
		return spnState;
	}

	public JColorChooserButton getBtnSelectColor() {
		return btnSelectColor;
	}

	public JMenuItem getMenuItemClear() {
		return menuItemClear;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JButton getBtnStop() {
		return btnStop;
	}

	public JButton getBtnZoomIn() {
		return btnZoomIn;
	}

	public JButton getBtnZoomOut() {
		return btnZoomOut;
	}

	public JLabel getLblStatus() {
		return lblStatus;
	}

	public Canvas3D getCanvas3D() {
		return canvas3D;
	}

	public JTextField getTxtDescrip() {
		return txtDescrip;
	}

	public JButton getBtnSaveImage() {
		return btnSaveImage;
	}

	public JButton getBtnArrowUp() {
		return btnArrowUp;
	}

	public JButton getBtnArrowDown() {
		return btnArrowDown;
	}

	public JButton getBtnArrowLeft() {
		return btnArrowLeft;
	}

	public JButton getBtnArrowRight() {
		return btnArrowRight;
	}

	public JLabel getLblIterationsStatus() {
		return lblIterationsStatus;
	}

	public JPanel getPnlCanvas() {
		return pnlCanvas;
	}

	public void setCanvas3D(Canvas3D canvas3d) {
		canvas3D = canvas3d;
	}

}
