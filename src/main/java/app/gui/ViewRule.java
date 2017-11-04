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
import app.Translate;
import app.util.JColorChooserButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ViewRule extends JDialog {

    private JLabel lblRule;
    private JTextField txtRule;
    private JButton btnAddRule;
    private JColorChooserButton btnSelectColor;
    private JLabel lblState;
    private JSpinner spnState;
    private JButton btnCancel;
    private JLabel lblColor;

    public ViewRule() {
        setSize(400, 200);
        setTitle(Config.get("APP_NAME"));

        JPanel panel = new JPanel(new MigLayout());
        add(panel);

        lblRule = new JLabel(Translate.get("GUI_RULE"));
        txtRule = new JTextField();
        btnAddRule = new JButton(Translate.get("GUI_ADD"));

        lblColor = new JLabel(Translate.get("GUI_COLOR"));
        lblState = new JLabel(Translate.get("GUI_STATE"));
        spnState = new JSpinner();

        btnSelectColor = new JColorChooserButton();

        btnCancel = new JButton(Translate.get("GUI_CANCEL"));

        JPanel buttons = new JPanel(new MigLayout("insets 0"));

        buttons.add(btnAddRule, "width 50%, height 40");
        buttons.add(btnCancel, "width 50%, height 40");

        panel.add(lblRule);
        panel.add(txtRule, "width 100%, wrap");
        panel.add(lblState);
        panel.add(spnState, "width 100, wrap");
        panel.add(lblColor);
        panel.add(btnSelectColor, "width 100, height 30, wrap 10");

        panel.add(buttons, "width 100%, span 2");

        setLocationRelativeTo(this);
    }

    public JTextField getTxtRule() {
        return txtRule;
    }

    public JButton getBtnAddRule() {
        return btnAddRule;
    }

    public JColorChooserButton getBtnSelectColor() {
        return btnSelectColor;
    }

    public JSpinner getSpnState() {
        return spnState;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setController(Controller controller) {
        btnAddRule.addActionListener(controller);
        btnCancel.addActionListener(controller);
    }
}
