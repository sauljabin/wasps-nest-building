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

public class Configuration {
	private int delay;
	private int iterations;
	private int agents;
	private int latticeSize;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getAgents() {
		return agents;
	}

	public void setAgents(int agents) {
		this.agents = agents;
	}

	public int getLatticeSize() {
		return latticeSize;
	}

	public void setLatticeSize(int latticeSize) {
		this.latticeSize = latticeSize;
	}

	public Configuration() {
	}

	public Configuration(int delay, int iterations, int agents, int latticeSize, String description) {
		this.delay = delay;
		this.iterations = iterations;
		this.agents = agents;
		this.latticeSize = latticeSize;
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Configuration [delay=%s, iterations=%s, agents=%s, latticeSize=%s, description=%s]", delay, iterations, agents, latticeSize, description);
	}

}
