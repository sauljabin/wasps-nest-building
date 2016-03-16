/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.WindowConstants;

import app.Config;

public abstract class View extends JFrame {

	private static final long serialVersionUID = 4405622666934862046L;
	private Controller controller;
	private Vector<JButton> buttons;
	private Vector<JCheckBox> checkBoxs;
	private Vector<JSpinner> spinners;
	private Vector<JComboBox<?>> comboBoxs;
	private Vector<JMenuItem> menuItems;

	public void addMenuItemToAction(JMenuItem menuItem) {
		menuItems.add(menuItem);
		if (controller == null)
			return;
		menuItem.addActionListener(controller);
	}

	public void addButtonToAction(JButton button) {
		buttons.add(button);
		if (controller == null)
			return;
		button.addActionListener(controller);
	}

	public void addComboBoxToAction(JComboBox<?> comboBox) {
		comboBoxs.add(comboBox);
		if (controller == null)
			return;
		comboBox.addItemListener(controller);
	}

	public void addSpinnerToAction(JSpinner spinner) {
		spinners.add(spinner);
		if (controller == null)
			return;
		spinner.addChangeListener(controller);
	}

	public void addCheckBoxToAction(JCheckBox checkBox) {
		checkBoxs.add(checkBox);
		if (controller == null)
			return;
		checkBox.addChangeListener(controller);
	}

	public View() {
		buttons = new Vector<JButton>();
		comboBoxs = new Vector<JComboBox<?>>();
		spinners = new Vector<JSpinner>();
		checkBoxs = new Vector<JCheckBox>();
		menuItems = new Vector<JMenuItem>();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle(Config.get("APP_NAME"));
		init();
		translate();
		setLocationRelativeTo(this);
	}

	public abstract void init();

	public void translate() {
	};

	public void setController(Controller controller) {
		removeController();
		this.controller = controller;
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).addActionListener(controller);
		}
		for (int i = 0; i < comboBoxs.size(); i++) {
			comboBoxs.get(i).addItemListener(controller);
		}
		for (int i = 0; i < spinners.size(); i++) {
			spinners.get(i).addChangeListener(controller);
		}
		for (int i = 0; i < checkBoxs.size(); i++) {
			checkBoxs.get(i).addChangeListener(controller);
		}
		for (int i = 0; i < menuItems.size(); i++) {
			menuItems.get(i).addActionListener(controller);
		}
		this.addWindowListener(controller);
	}

	public void removeController() {
		if (controller == null)
			return;
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).removeActionListener(controller);
		}
		for (int i = 0; i < comboBoxs.size(); i++) {
			comboBoxs.get(i).removeItemListener(controller);
		}
		for (int i = 0; i < spinners.size(); i++) {
			spinners.get(i).removeChangeListener(controller);
		}
		for (int i = 0; i < checkBoxs.size(); i++) {
			checkBoxs.get(i).removeChangeListener(controller);
		}
		for (int i = 0; i < menuItems.size(); i++) {
			menuItems.get(i).removeActionListener(controller);
		}
		this.removeWindowListener(controller);
		controller = null;
	}

	public Controller getController() {
		return controller;
	}

}
