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
	};

	public Cell(int x, int y, int z, int state, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.state = state;
		this.color = color;
	}
}
