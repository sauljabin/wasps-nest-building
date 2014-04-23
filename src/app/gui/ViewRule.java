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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import app.Config;
import app.Translate;
import app.util.JColorChooserButton;

public class ViewRule extends JDialog {

	private static final long serialVersionUID = -797497423841324268L;
	private JLabel lblRule;
	private JTextField txtRule;
	private JButton btnAddRule;
	private JColorChooserButton btnSelectColor;
	private JLabel lblState;
	private JSpinner spnState;
	private JButton btnCancel;
	private JLabel lblColor;

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

	public void setController(Controller controller) {
		btnAddRule.addActionListener(controller);
		btnCancel.addActionListener(controller);
	}
}
