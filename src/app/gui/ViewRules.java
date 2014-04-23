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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import app.Config;

public class ViewRules extends JDialog {

	private static final long serialVersionUID = -797497423841324268L;
	private JButton btnAddRule;
	private JButton btnDeleteRule;
	private JButton btnEditRule;
	private JScrollPane scrollPanelRules;
	private JTable tableRules;

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

	public ViewRules(boolean simulationStarted) {
		setSize(500, 300);
		setTitle(Config.get("APP_NAME"));

		JPanel panel = new JPanel(new MigLayout());
		add(panel);

		JPanel panelButton = new JPanel(new MigLayout());

		btnAddRule = new JButton(new ImageIcon("img/plus16px.png"));
		btnDeleteRule = new JButton(new ImageIcon("img/minus16px.png"));
		btnEditRule = new JButton(new ImageIcon("img/edit16px.png"));

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

	public void setController(Controller controller) {
		btnAddRule.addActionListener(controller);
		btnDeleteRule.addActionListener(controller);
		btnEditRule.addActionListener(controller);
		tableRules.addMouseListener(controller);
	}
}
