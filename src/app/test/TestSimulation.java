package app.test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import app.simulation.Agent;
import app.simulation.Cell;
import app.simulation.Importer;
import app.simulation.Rule;
import app.simulation.Simulation;
import app.util.UtilDate;

public class TestSimulation {

	private static PrintWriter pw;

	public static void printLattice(Simulation simulation) {
		Cell[][][] lattice = simulation.getLattice();
		int latticeSize = lattice.length;
		println("Lattice:");
		for (int z = latticeSize - 1; z >= 0; z--) {
			println("z=" + z);
			for (int y = 0; y < latticeSize; y++) {
				for (int x = 0; x < latticeSize; x++) {
					int state = lattice[x][y][z].getState();
					print(String.format("%3s", state));
				}
				println();
			}
			println();
		}
		println();
	}

	public static void printAgents(Simulation simulation) {
		Agent[] agents = simulation.getAgents();
		println("Agents:");
		for (Agent agent : agents) {
			String n = simulation.getNeighbourhood(agent);
			println(agent + " Neighbourhood: " + n);
		}
		println();
	}

	public static void println(Object o) {
		System.out.println(o);
		pw.println(o);
	}

	public static void print(Object o) {
		System.out.print(o);
		pw.print(o);
	}

	public static void println() {
		println("");
	}

	public static void printRules(Simulation simulation) {
		Rule[] rules = simulation.getRules();
		println("Rules:");
		for (Rule rule : rules) {
			println(rule);
		}
		println();
	}

	public static void printImporter(Importer importer) {
		println("Path File:");
		println(importer.getPath());
		println();
		println("Configuration:");
		println(importer.getConfiguration());
		println();
		println("Initial Cell:");
		println(importer.getInitialCell());
		println();
	}

	public static void pressIntro() throws Exception {
		println("PRESS INTRO...");
		System.in.read();
	}

	public static void initFileOutput() throws Exception {
		File folder = new File("test");
		folder.mkdir();
		pw = new PrintWriter(new FileWriter(folder.getPath() + "/RESULTS-" + UtilDate.nowFormat("yyyy-MM-dd") + ".txt"), true);
	}

	public static void main(String[] args) throws Exception {

		initFileOutput();

		// IMPORTER
		Importer importer = new Importer("data/StructureHelicoidale.xml");
		importer.importXML();

		printImporter(importer);

		// SIMULATION
		int tmax = importer.getConfiguration().getIterations();
		int m = importer.getConfiguration().getAgents();
		int latticeSize = importer.getConfiguration().getLatticeSize();
		Rule[] rules = importer.getRules().toArray(new Rule[importer.getRules().size()]);
		Cell initialCell = importer.getInitialCell();

		Simulation simulation = new Simulation(tmax, m, latticeSize, initialCell, rules);
		simulation.setDelay(0);

		printRules(simulation);
		printAgents(simulation);
		printLattice(simulation);

		simulation.run();

		println("Results:");
		printLattice(simulation);

	}
}
