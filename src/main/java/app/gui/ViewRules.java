/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of WaspsNestBuilding.
 * <p>
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import app.Config;
import app.util.ImageLoader;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ViewRules extends JDialog {

    private JButton btnAddRule;
    private JButton btnDeleteRule;
    private JButton btnEditRule;
    private JScrollPane scrollPanelRules;
    private JTable tableRules;

    public ViewRules(boolean simulationStarted) {
        setSize(500, 300);
        setTitle(Config.get("APP_NAME"));

        JPanel panel = new JPanel(new MigLayout());
        add(panel);

        JPanel panelButton = new JPanel(new MigLayout());

        btnAddRule = new JButton(ImageLoader.loadImage("plus16px"));
        btnDeleteRule = new JButton(ImageLoader.loadImage("minus16px"));
        btnEditRule = new JButton(ImageLoader.loadImage("edit16px"));

        scrollPanelRules = new JScrollPane();
        tableRules = new JTable();
        scrollPanelRules.setViewportView(tableRules);

        panelButton.add(btnAddRule);
        panelButton.add(btnDeleteRule);
        panelButton.add(btnEditRule);
        if (!simulationStarted) {
            panel.add(panelButton, "width 100%, wrap 10");
        }
        panel.add(scrollPanelRules, "width 100%");

        setLocationRelativeTo(this);
    }

    public JButton getBtnAddRule() {
        return btnAddRule;
    }

    public JButton getBtnEditRule() {
        return btnEditRule;
    }

    public JButton getBtnDeleteRule() {
        return btnDeleteRule;
    }

    public JTable getTableRules() {
        return tableRules;
    }

    public void setController(Controller controller) {
        btnAddRule.addActionListener(controller);
        btnDeleteRule.addActionListener(controller);
        btnEditRule.addActionListener(controller);
        tableRules.addMouseListener(controller);
    }
}
