/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
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
