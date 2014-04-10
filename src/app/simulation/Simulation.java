package app.simulation;

import java.awt.Color;

public class Simulation implements Runnable {

	public static final int AGENT_STATE = -1;

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

	public Simulation(int tmax, int m, int latticeSize, Rule[] rules) {
		this.tmax = tmax;
		this.m = m;
		this.latticeSize = latticeSize;
		this.rules = rules;
		this.delay = 40;

		createLattice();
		createAgents();
	}

	private void createAgents() {
		agents = new Agent[m];
		for (int k = 0; k < m; k++) {
			agents[k] = new Agent();
		}
	}

	private void createLattice() {
		lattice = new Cell[latticeSize][latticeSize][latticeSize];
		for (int x = 0; x < latticeSize; x++) {
			for (int y = 0; y < latticeSize; y++) {
				for (int z = 0; z < latticeSize; z++) {
					lattice[x][y][z] = new Cell(x, y, z, 0, null);
				}
			}
		}
	}

	public Rule findRule(String neighbourhood) {
		for (int i = 0; i < rules.length; i++) {
			if (rules[i].getNeighbourhood().equals(neighbourhood))
				return rules[i];
		}
		return null;
	}

	public String getNeighbourhood(Agent agent) {
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

	public void updateCell(int x, int y, int z, int state, Color color) {
		Cell cell = lattice[x][y][z];
		cell.setState(state);
		cell.setColor(color);
	}

	@Override
	public void run() {
		while (!stop && iterations <= tmax) {

			for (int k = 0; k < m; k++) {
				Agent agent = agents[k];
				String neighbourhood = getNeighbourhood(agent);
				Rule rule = findRule(neighbourhood);
				if (rule != null)
					updateCell(agent.getX(), agent.getY(), agent.getZ(), rule.getState(), rule.getColor());

			}

			iterations++;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
