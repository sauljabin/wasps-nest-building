/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.simulation;

import java.io.FileWriter;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Exporter {
	private List<Rule> rules;
	private Cell initialCell;
	private Configuration configuration;
	private String path;

	public Exporter(String path, Configuration configuration, Cell initialCell, List<Rule> rules) {
		this.rules = rules;
		this.initialCell = initialCell;
		this.configuration = configuration;
		this.path = path;
	}

	public Exporter(String path) {
		this.path = path;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public void setInitialCell(Cell initialCell) {
		this.initialCell = initialCell;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void exportXML() throws Exception {
		Element root = new Element("simulation");
		Document document = new Document(root);

		if (configuration != null) {
			Element tagConfiguration = new Element("configuration");

			Element delay = new Element("delay");
			delay.setText(String.format("%s", configuration.getDelay()));

			Element iterations = new Element("iterations");
			iterations.setText(String.format("%s", configuration.getIterations()));

			Element agents = new Element("agents");
			agents.setText(String.format("%s", configuration.getAgents()));

			Element latticeSize = new Element("latticeSize");
			latticeSize.setText(String.format("%s", configuration.getLatticeSize()));

			Element description = new Element("description");
			description.setText(String.format("%s", configuration.getDescription()));

			tagConfiguration.addContent(delay);
			tagConfiguration.addContent(iterations);
			tagConfiguration.addContent(agents);
			tagConfiguration.addContent(latticeSize);
			tagConfiguration.addContent(description);

			root.addContent(tagConfiguration);
		}

		if (initialCell != null) {
			Element tagInitialCell = new Element("initialCell");

			Element x = new Element("x");
			x.setText(String.format("%s", initialCell.getX()));

			Element y = new Element("y");
			y.setText(String.format("%s", initialCell.getY()));

			Element z = new Element("z");
			z.setText(String.format("%s", initialCell.getZ()));

			Element state = new Element("state");
			state.setText(String.format("%s", initialCell.getState()));

			Element color = new Element("color");

			Element r = new Element("r");
			r.setText(String.format("%s", initialCell.getColor().getRed()));

			Element g = new Element("g");
			g.setText(String.format("%s", initialCell.getColor().getGreen()));

			Element b = new Element("b");
			b.setText(String.format("%s", initialCell.getColor().getBlue()));

			color.addContent(r);
			color.addContent(g);
			color.addContent(b);

			tagInitialCell.addContent(x);
			tagInitialCell.addContent(y);
			tagInitialCell.addContent(z);
			tagInitialCell.addContent(state);
			tagInitialCell.addContent(color);

			root.addContent(tagInitialCell);
		}

		if (rules != null) {
			Element tagRules = new Element("rules");

			for (Rule rule : rules) {
				Element tagRule = new Element("rule");

				Element neighbourhood = new Element("neighbourhood");
				neighbourhood.setText(rule.getNeighbourhood());

				Element state = new Element("state");
				state.setText(String.format("%s", rule.getState()));

				Element color = new Element("color");

				Element r = new Element("r");
				r.setText(String.format("%s", rule.getColor().getRed()));

				Element g = new Element("g");
				g.setText(String.format("%s", rule.getColor().getGreen()));

				Element b = new Element("b");
				b.setText(String.format("%s", rule.getColor().getBlue()));

				color.addContent(r);
				color.addContent(g);
				color.addContent(b);

				tagRule.addContent(neighbourhood);
				tagRule.addContent(state);
				tagRule.addContent(color);

				tagRules.addContent(tagRule);
			}

			root.addContent(tagRules);
		}

		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(document, new FileWriter(path));
	}

}
