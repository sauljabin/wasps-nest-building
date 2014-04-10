package app.simulation;

import java.awt.Color;

public class Rule {
	private int state;
	private Color color;
	private String neighbourhood;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

}
