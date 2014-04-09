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
	private JLabel lblShowColor;
	private JButton btnSelectColor;
	private JMenuItem menuItemClean;
	private JButton btnAddBlock;
	private JButton btnStart;
	private JButton btnStop;
	private JPanel pnlCenter;
	private JPanel pnlStatus;
	private JButton btnZoomIn;
	private JButton btnZoomOut;
	private JLabel lblStatus;
	private JButton btnLeftCam;
	private JButton btnRightCam;
	private JButton btnUpCam;
	private JButton btnDownCam;
	private Canvas3D canvas3D;
	private JLabel lblDescrip;
	private JTextField txtDescrip;
	private JPanel pnlDescrip;
	private JPanel pnlCanvas;

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
		menuItemClean = new JMenuItem();
		menuItemImportConfig = new JMenuItem();
		menuItemExportConfig = new JMenuItem();
		menuItemClose = new JMenuItem();
		menuOptions.add(menuItemShowConfig);
		menuOptions.add(menuItemShowRules);
		menuOptions.add(new JSeparator());
		menuOptions.add(menuItemClean);
		menuOptions.add(menuItemImportConfig);
		menuOptions.add(menuItemExportConfig);
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

		pnlConfig.add(lblDelay, "width 90");
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
		lblShowColor = new JLabel();
		lblShowColor.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnSelectColor = new JButton(" ... ");

		btnAddBlock = new JButton();

		pnlBlock.add(lblBlockX, "width 90");
		pnlBlock.add(spnBlockX, "width 100, wrap");
		pnlBlock.add(lblBlockY, "grow");
		pnlBlock.add(spnBlockY, "grow, wrap");
		pnlBlock.add(lblBlockZ, "grow");
		pnlBlock.add(spnBlockZ, "grow, wrap");
		pnlBlock.add(lblState, "grow");
		pnlBlock.add(spnState, "grow, wrap");
		pnlBlock.add(lblColor, "grow");
		pnlBlock.add(lblShowColor, "split 2, grow");
		pnlBlock.add(btnSelectColor, "wrap 10");
		pnlBlock.add(btnAddBlock, "height 25, grow, span 2, wrap");

		btnStart = new JButton();
		btnStop = new JButton();

		pnlWest.add(pnlConfig, "width 210, wrap");
		pnlWest.add(pnlBlock, "width 210, wrap 20");
		pnlWest.add(btnStart, "height 30, width 210, wrap");
		pnlWest.add(btnStop, "height 30, width 210, wrap");

		add(pnlWest, BorderLayout.WEST);
		// WEST

		// CENTER
		pnlCenter = new JPanel(new BorderLayout());

		pnlStatus = new JPanel(new MigLayout());

		lblStatus = new JLabel();

		btnLeftCam = new JButton(new ImageIcon("img/arrow-left20px.png"));
		btnRightCam = new JButton(new ImageIcon("img/arrow-right20px.png"));
		btnUpCam = new JButton(new ImageIcon("img/arrow-up20px.png"));
		btnDownCam = new JButton(new ImageIcon("img/arrow-down20px.png"));
		btnZoomIn = new JButton(new ImageIcon("img/zoom-in20px.png"));
		btnZoomOut = new JButton(new ImageIcon("img/zoom-out20px.png"));

		pnlStatus.add(lblStatus, "width 100%");
		pnlStatus.add(btnUpCam);
		pnlStatus.add(btnDownCam);
		pnlStatus.add(btnLeftCam);
		pnlStatus.add(btnRightCam);
		pnlStatus.add(btnZoomIn);
		pnlStatus.add(btnZoomOut);
		pnlCenter.add(pnlStatus, BorderLayout.SOUTH);

		pnlDescrip = new JPanel(new MigLayout());
		lblDescrip = new JLabel();
		txtDescrip = new JTextField();
		pnlDescrip.add(lblDescrip);
		pnlDescrip.add(txtDescrip, "width 100%");
		pnlCenter.add(pnlDescrip, BorderLayout.NORTH);

		// CANVAS 3D
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas3D = new Canvas3D(config);
		pnlCanvas = new JPanel(new BorderLayout());
		pnlCanvas.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 7));
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
		addMenuItemToAction(menuItemClean);

		addButtonToAction(btnAddBlock);
		addButtonToAction(btnSelectColor);
		addButtonToAction(btnStart);
		addButtonToAction(btnStop);
		addButtonToAction(btnZoomIn);
		addButtonToAction(btnZoomOut);
		addButtonToAction(btnLeftCam);
		addButtonToAction(btnRightCam);
		addButtonToAction(btnDownCam);
		addButtonToAction(btnUpCam);

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
		menuItemClean.setText(Translate.get("GUI_CLEAN"));
		menuItemExportConfig.setText(Translate.get("GUI_EXPORTCONFIG"));
		menuItemImportConfig.setText(Translate.get("GUI_IMPORTCONFIG"));
		pnlConfigBorder.setTitle(Translate.get("GUI_CONFIG"));
		pnlBlockBorder.setTitle(Translate.get("GUI_BLOCK"));
		lblIterations.setText(Translate.get("GUI_ITERATIONS"));
		lblDelay.setText(Translate.get("GUI_DELAY"));
		lblAgents.setText(Translate.get("GUI_AGENTS"));
		lblCellsPerAxis.setText(Translate.get("GUI_CELLSPERAXIS"));
		lblBlockX.setText(Translate.get("GUI_X"));
		lblBlockY.setText(Translate.get("GUI_Y"));
		lblBlockZ.setText(Translate.get("GUI_Z"));
		lblState.setText(Translate.get("GUI_STATE"));
		lblColor.setText(Translate.get("GUI_COLOR"));
		btnAddBlock.setText(Translate.get("GUI_ADD"));
		btnStart.setText(Translate.get("GUI_START"));
		btnStop.setText(Translate.get("GUI_STOP"));
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

	public JButton getBtnSelectColor() {
		return btnSelectColor;
	}

	public JMenuItem getMenuItemClean() {
		return menuItemClean;
	}

	public JButton getBtnAddBlock() {
		return btnAddBlock;
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

	public JLabel getLblShowColor() {
		return lblShowColor;
	}

	public JButton getBtnLeftCam() {
		return btnLeftCam;
	}

	public JButton getBtnRightCam() {
		return btnRightCam;
	}

	public JButton getBtnUpCam() {
		return btnUpCam;
	}

	public JButton getBtnDownCam() {
		return btnDownCam;
	}

	public Canvas3D getCanvas3D() {
		return canvas3D;
	}

	public JTextField getTxtDescrip() {
		return txtDescrip;
	}

}
