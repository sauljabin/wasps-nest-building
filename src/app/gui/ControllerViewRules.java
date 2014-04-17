package app.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import app.Config;
import app.Translate;
import app.simulation.Rule;

public class ControllerViewRules extends Controller {

	private ViewRules viewRules;
	private SpinnerNumberModel spnStateModel;
	private List<Rule> rules;

	public ControllerViewRules(List<Rule> rules) {
		this.rules = rules;
		viewRules = new ViewRules();
		viewRules.setController(this);
		spnStateModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_STATE")), 0, Integer.parseInt(Config.get("MAX_STATE")), 1);
		viewRules.getSpnState().setModel(spnStateModel);
		viewRules.getLblShowColor().setOpaque(true);
		viewRules.getLblShowColor().setBackground(Color.BLACK);
		new RulesModelTable(viewRules.getTableRules());
		new RulesRendererCellTable(viewRules.getTableRules());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				viewRules.setModal(true);
				viewRules.setVisible(true);
			}
		});

	}

	public void selectColor() {
		Color selectedColor = JColorChooser.showDialog(viewRules, Translate.get("GUI_SELECTCOLOR"), viewRules.getLblShowColor().getBackground());
		if (selectedColor != null)
			viewRules.getLblShowColor().setBackground(selectedColor);
	}

	@Override
	public void action(Object source) {
		if (source.equals(viewRules.getBtnSelectColor()))
			selectColor();
		if (source.equals(viewRules))
			close();
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
			if (column == 3) {
				this.setBackground(rules.get(row).getColor());
				table.setSelectionBackground(rules.get(row).getColor());
			} else {
				this.setBackground(background);
				table.setSelectionBackground(selectionColor);
			}

			// if (column == 0)
			// this.setHorizontalAlignment(SwingConstants.CENTER);
			// else
			// this.setHorizontalAlignment(SwingConstants.LEFT);

			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			return this;
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

	}

}
