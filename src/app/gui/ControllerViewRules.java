package app.gui;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import app.Config;
import app.Translate;

public class ControllerViewRules extends Controller {

	private ViewRules viewRules;
	private SpinnerNumberModel spnStateModel;

	public ControllerViewRules() {
		viewRules = new ViewRules();
		viewRules.setController(this);
		spnStateModel = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_STATE")), 0, Integer.parseInt(Config.get("MAX_STATE")), 1);
		viewRules.getSpnState().setModel(spnStateModel);
		viewRules.getLblShowColor().setOpaque(true);
		viewRules.getLblShowColor().setBackground(Color.BLACK);
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

}
