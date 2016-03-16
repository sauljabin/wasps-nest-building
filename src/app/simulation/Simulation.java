/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.simulation;

import java.util.Random;

public class Simulation implements Runnable {

	public static final int STATE_AGENT = -1;
	public static final int STATE_UNOCCUPIED = 0;

	private Thread thread;
	private int tmax;
	private int m;
	private int latticeSize;
	private Rule[] rules;
	private Agent[] agents;
	private Cell[][][] lattice;
	private int delay;
	private int iterations;
	private boolean stop;
	private Random random;
	private Cell initialCell;

	public int getIterations() {
		return iterations;
	}

	public int getTmax() {
		return tmax;
	}

	public void setTmax(int tmax) {
		this.tmax = tmax;
	}

	public int getM() {
		return m;
	}

	public int getLatticeSize() {
		return latticeSize;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public Rule[] getRules() {
		return rules;
	}

	public Agent[] getAgents() {
		return agents;
	}

	public Cell[][][] getLattice() {
		return lattice;
	}

	public boolean isStop() {
		return stop;
	}

	public Cell getInitialCell() {
		return initialCell;
	}

	public Simulation(Configuration configuration, Cell initialCell, Rule[] rules) {
		this.tmax = configuration.getIterations();
		this.m = configuration.getAgents();
		this.latticeSize = configuration.getLatticeSize();
		this.rules = rules;
		this.delay = configuration.getDelay();
		this.initialCell = initialCell;

		random = new Random();

		createLattice();
		createAgents();
	}

	public void createAgents() {
		agents = new Agent[m];
		for (int k = 0; k < m; k++) {
			agents[k] = createAgent();
			moveRandomly(agents[k]);
		}
	}

	public Agent createAgent() {
		return new Agent();
	}

	public Cell createCell() {
		return new Cell();
	}

	public void createLattice() {
		lattice = new Cell[latticeSize][latticeSize][latticeSize];
		for (int x = 0; x < latticeSize; x++) {
			for (int y = 0; y < latticeSize; y++) {
				for (int z = 0; z < latticeSize; z++) {
					Cell cell = createCell();
					lattice[x][y][z] = cell;
					cell.setX(x);
					cell.setY(y);
					cell.setZ(z);
					cell.setState(STATE_UNOCCUPIED);
				}
			}
		}

		lattice[initialCell.getX()][initialCell.getY()][initialCell.getZ()].setState(initialCell.getState());
		lattice[initialCell.getX()][initialCell.getY()][initialCell.getZ()].setColor(initialCell.getColor());
	}

	public Rule getRule(String neighbourhood) {
		for (int i = 0; i < rules.length; i++) {
			if (rules[i].getNeighbourhood().equals(neighbourhood))
				return rules[i];
		}
		return null;
	}

	public void start() {
		if (!isAlive()) {
			thread = new Thread(this);
			thread.start();
			stop = false;
		}
	}

	public boolean isAlive() {
		return thread == null ? false : thread.isAlive();
	}

	public void stop() {
		stop = true;
		try {
			if (isAlive())
				thread.join(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveRandomly(Agent agent) {

		Cell cell = lattice[agent.getX()][agent.getY()][agent.getZ()];
		if (cell.getState() == STATE_AGENT)
			cell.setState(STATE_UNOCCUPIED);

		int x, y, z = 0;

		while (true) {
			x = random.nextInt(latticeSize);
			y = random.nextInt(latticeSize);
			z = random.nextInt(latticeSize);
			cell = lattice[x][y][z];
			if (cell.getState() == STATE_UNOCCUPIED) {
				agent.setX(x);
				agent.setY(y);
				agent.setZ(z);
				cell.setState(STATE_AGENT);
				break;
			}
		}
	}

	public String getNeighbourhood(Agent agent) {

		String string = "";

		for (int z = agent.getZ() + 1; z >= agent.getZ() - 1; z--) {
			int x = agent.getX();
			int y = agent.getY();
			int pos[] = new int[9];

			if (z < latticeSize && z >= 0) {

				if (y - 1 >= 0)
					pos[0] = lattice[x][y - 1][z].getState();

				if (x + 1 < latticeSize && y - 1 >= 0)
					pos[1] = lattice[x + 1][y - 1][z].getState();

				if (x + 1 < latticeSize)
					pos[2] = lattice[x + 1][y][z].getState();

				if (x + 1 < latticeSize && y + 1 < latticeSize)
					pos[3] = lattice[x + 1][y + 1][z].getState();

				if (y + 1 < latticeSize)
					pos[4] = lattice[x][y + 1][z].getState();

				if (x - 1 >= 0 && y + 1 < latticeSize)
					pos[5] = lattice[x - 1][y + 1][z].getState();

				if (x - 1 >= 0)
					pos[6] = lattice[x - 1][y][z].getState();

				if (x - 1 >= 0 && y - 1 >= 0)
					pos[7] = lattice[x - 1][y - 1][z].getState();

				pos[8] = lattice[x][y][z].getState();
			}

			for (int i = 0; i < 9; i++) {
				if (pos[i] == STATE_AGENT)
					pos[i] = 0;

				if (agent.getZ() == z && i == 8)
					continue;

				string += pos[i] + ",";
			}

		}

		return string.substring(0, string.length() - 1);
	}

	@Override
	public void run() {
		while (!stop && iterations <= tmax) {

			for (int k = 0; k < m; k++) {
				Agent agent = agents[k];
				String neighbourhood = getNeighbourhood(agent);
				updateCell(lattice[agent.getX()][agent.getY()][agent.getZ()], getRule(neighbourhood));
				moveRandomly(agent);
			}
			updateIterations();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		endSimulation();
	}

	public void endSimulation() {
		stop();
	}

	public void updateIterations() {
		iterations++;
	}

	public void updateCell(Cell cell, Rule rule) {
		if (rule != null) {
			cell.setState(rule.getState());
			cell.setColor(rule.getColor());
		}
	}
}
