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
