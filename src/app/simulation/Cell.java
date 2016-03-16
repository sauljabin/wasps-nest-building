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

public class Cell {
	private int x;
	private int y;
	private int z;
	private int state;
	private Color color;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

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

	public Cell() {
	}

	public Cell(int x, int y, int z, int state, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.state = state;
		this.color = color;
	}

	@Override
	public String toString() {
		return String.format("Cell [x=%s, y=%s, z=%s, state=%s, color=[r=%s, g=%s, b=%s]]", x, y, z, state, color.getRed(), color.getGreen(), color.getGreen());
	}

}
