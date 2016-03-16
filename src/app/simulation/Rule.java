/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

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

	public Rule() {
	}

	public Rule(String neighbourhood, int state, Color color) {
		this.state = state;
		this.color = color;
		this.neighbourhood = neighbourhood;
	}

	@Override
	public String toString() {
		return String.format("Rule [neighbourhood=%s, state=%s, color=[r=%s, g=%s, b=%s]]", neighbourhood, state, color.getRed(), color.getGreen(), color.getGreen());
	}

}
