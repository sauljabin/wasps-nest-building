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

		random = new Random();

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
					lattice[x][y][z] = new Cell(x, y, z, STATE_UNOCCUPIED, null);
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
		
		
		
		return null;
	}

	@Override
	public void run() {

		for (int k = 0; k < m; k++) {
			Agent agent = agents[k];
			moveRandomly(agent);
		}

		while (!stop && iterations <= tmax) {

			for (int k = 0; k < m; k++) {
				Agent agent = agents[k];
				String neighbourhood = getNeighbourhood(agent);
				Rule rule = findRule(neighbourhood);
				Cell cell = lattice[agent.getX()][agent.getY()][agent.getZ()];
				if (rule != null) {
					cell.setState(rule.getState());
					cell.setColor(rule.getColor());
				}
				moveRandomly(agent);
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
