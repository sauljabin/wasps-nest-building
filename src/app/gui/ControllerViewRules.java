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
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import app.Config;
import app.Translate;
import app.simulation.Rule;

public class ControllerViewRules extends Controller {

	private ViewRules viewRules;
	private ViewRule viewRule;
	private SpinnerNumberModel spnStateModel;
	private List<Rule> rules;
	private RulesModelTable rulesModelTable;
	private Rule ruleSelected;

	public ControllerViewRules(List<Rule> rules, boolean simulationStarted) {
		this.rules = rules;

		viewRules = new ViewRules(simulationStarted);
		viewRules.setController(this);
		initState();

		rulesModelTable = new RulesModelTable(viewRules.getTableRules());
		new RulesRendererCellTable(viewRules.getTableRules());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				viewRules.setModal(true);
				viewRules.setVisible(true);
			}
		});

	}

	public void initState() {
		viewRules.getBtnDeleteRule().setEnabled(false);
		viewRules.getBtnEditRule().setEnabled(false);
	}

	@Override
	public void action(Object source) {
		if (source.equals(viewRules))
			close();
		else if (source.equals(viewRules.getBtnAddRule()))
			newRule();
		else if (source.equals(viewRules.getBtnDeleteRule()))
			deleteRule();
		else if (source.equals(viewRules.getBtnEditRule()))
			showEdit();

		if (viewRule != null) {
			if (source.equals(viewRule.getBtnCancel()))
				closeEdit();
			else if (source.equals(viewRule.getBtnAddRule()))
				addRule();
		}
	}

	public void closeEdit() {
		viewRule.dispose();
		viewRule = null;
	}

	public void deleteRule() {
		List<Rule> delete = rulesModelTable.getSelection();
		for (Rule rule : delete) {
			rules.remove(rule);
		}
		rulesModelTable = new RulesModelTable(viewRules.getTableRules());
		initState();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		viewRules.getBtnDeleteRule().setEnabled(true);
		viewRules.getBtnEditRule().setEnabled(true);
	}

	public void addRule() {
		if (ruleSelected == null) {
			ruleSelected = new Rule();
			rules.add(ruleSelected);
		}
		ruleSelected.setColor(viewRule.getBtnSelectColor().getColor());
		ruleSelected.setState((Integer) viewRule.getSpnState().getValue());
		ruleSelected.setNeighbourhood(viewRule.getTxtRule().getText());
		closeEdit();
		rulesModelTable = new RulesModelTable(viewRules.getTableRules());
		initState();
	}

	public void newRule() {

		viewRule = new ViewRule();

		spnStateModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_STATE")), 0, Integer.parseInt(Config.get("MAX_STATE")), 1);
		viewRule.getSpnState().setModel(spnStateModel);

		viewRule.setController(this);

		ruleSelected = null;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				viewRule.setModal(true);
				viewRule.setVisible(true);
			}
		});

	}

	public void showEdit() {

		viewRule = new ViewRule();

		spnStateModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_STATE")), 0, Integer.parseInt(Config.get("MAX_STATE")), 1);
		viewRule.getSpnState().setModel(spnStateModel);

		viewRule.setController(this);

		if (rulesModelTable.getSelection().size() > 0) {
			ruleSelected = rulesModelTable.getSelection().get(0);
			viewRule.getTxtRule().setText(ruleSelected.getNeighbourhood());
			viewRule.getBtnSelectColor().setColor(ruleSelected.getColor());
			viewRule.getSpnState().setValue(ruleSelected.getState());
		} else {
			ruleSelected = null;
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				viewRule.setModal(true);
				viewRule.setVisible(true);
			}
		});

	}

	public void close() {
		viewRules.dispose();
	}

	public class RulesRendererCellTable extends DefaultTableCellRenderer {
		private static final long serialVersionUID = -8181325485243567974L;
		private JTable table;
		private Color background;
		private Color selectionColor;

		public RulesRendererCellTable(JTable table) {
			this.table = table;
			this.setOpaque(true);
			this.table.setDefaultRenderer(Object.class, this);
			background = getBackground();
			selectionColor = table.getSelectionBackground();
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if (column == 3)
				c.setBackground(rules.get(row).getColor());
			else if (isSelected)
				c.setBackground(selectionColor);
			else
				c.setBackground(background);

			if (column == 1)
				this.setHorizontalAlignment(SwingConstants.LEFT);
			else
				this.setHorizontalAlignment(SwingConstants.CENTER);

			return c;
		}

	}

	public class RulesModelTable extends AbstractTableModel {

		private static final long serialVersionUID = -5598858618211316857L;
		private Vector<String> titles;
		private JTable table;

		public RulesModelTable(JTable table) {
			titles = new Vector<String>();
			titles.add("#");
			titles.add(Translate.get("GUI_RULE"));
			titles.add(Translate.get("GUI_STATE"));
			titles.add(Translate.get("GUI_COLOR"));
			this.table = table;
			this.table.setModel(this);
			this.table.getColumnModel().getColumn(0).setMaxWidth(30);
			this.table.getColumnModel().getColumn(2).setMaxWidth(50);
			this.table.getColumnModel().getColumn(3).setMaxWidth(50);
			this.table.setRowHeight(30);
		}

		@Override
		public int getColumnCount() {
			return titles.size();
		}

		@Override
		public int getRowCount() {
			return rules.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return rowIndex + 1;
			case 1:
				return rules.get(rowIndex).getNeighbourhood();
			case 2:
				return rules.get(rowIndex).getState();
			case 3:
				return "";
			}
			return null;
		}

		@Override
		public String getColumnName(int column) {
			return titles.get(column);
		}

		public List<Rule> getSelection() {
			int[] selection = table.getSelectedRows();
			List<Rule> selectionRules = new ArrayList<Rule>();
			for (int i = 0; i < selection.length; i++) {
				selectionRules.add(rules.get(table.convertRowIndexToModel(selection[i])));
			}
			return selectionRules;
		}

	}

}
