package app.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import app.Config;
import app.Translate;

public class ViewRules extends JDialog {

	private static final long serialVersionUID = -797497423841324268L;
	private JLabel lblRule;
	private JTextField txtRule;
	private JButton btnAddRule;
	private JLabel lblColor;
	private JLabel lblShowColor;
	private JButton btnSelectColor;
	private JLabel lblState;
	private JSpinner spnState;
	private JButton btnDeleteRule;
	private JScrollPane scrollPanelRules;
	private JTable tableRules;

	public JTextField getTxtRule() {
		return txtRule;
	}

	public JButton getBtnAddRule() {
		return btnAddRule;
	}

	public JLabel getLblShowColor() {
		return lblShowColor;
	}

	public JButton getBtnSelectColor() {
		return btnSelectColor;
	}

	public JSpinner getSpnState() {
		return spnState;
	}

	public JButton getBtnDeleteRule() {
		return btnDeleteRule;
	}

	public JTable getTableRules() {
		return tableRules;
	}

	public ViewRules() {
		setSize(500, 300);
		setTitle(Config.get("APP_NAME"));

		JPanel panel = new JPanel(new MigLayout());
		add(panel);

		lblRule = new JLabel(Translate.get("GUI_RULE"));
		txtRule = new JTextField();
		btnAddRule = new JButton(Translate.get("GUI_ADD"));

		lblState = new JLabel(Translate.get("GUI_STATE"));
		spnState = new JSpinner();

		lblColor = new JLabel(Translate.get("GUI_COLOR"));
		lblShowColor = new JLabel();
		lblShowColor.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnSelectColor = new JButton(" ... ");

		btnDeleteRule = new JButton(Translate.get("GUI_DELETE"));

		scrollPanelRules = new JScrollPane();
		tableRules = new JTable();
		scrollPanelRules.setViewportView(tableRules);

		panel.add(lblRule, "gapright 30");
		panel.add(txtRule, "width 100%, wrap");
		panel.add(lblState);
		panel.add(spnState, "split 2, width 100, gapright 220");
		panel.add(btnAddRule, "width 100, wrap");
		panel.add(lblColor);
		panel.add(lblShowColor, "split 3, width 80, height 20");
		panel.add(btnSelectColor, "gapright 220");
		panel.add(btnDeleteRule, "width 100, wrap 10");
		panel.add(scrollPanelRules, "span 2, width 100%");

		setLocationRelativeTo(this);		
	}

	public void setController(Controller controller) {
		btnAddRule.addActionListener(controller);
		btnDeleteRule.addActionListener(controller);
		btnSelectColor.addActionListener(controller);
	}
}
