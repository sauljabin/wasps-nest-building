package app.simulation;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import app.exception.AttributeNotFoundException;
import app.exception.InvalidFileFormatException;
import app.util.UtilFile;

public class Importer {
	private List<Rule> rules;
	private Cell initialCell;
	private Configuration configuration;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public Cell getInitialCell() {
		return initialCell;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public Importer(String path) {
		this.path = path;
	}

	public String getFileFormat() {
		return UtilFile.getExtension(path);
	}

	public void importXML() throws Exception {
		File file = new File(path);

		if (!file.exists())
			throw new FileNotFoundException(path);

		if (!getFileFormat().equalsIgnoreCase("xml"))
			throw new InvalidFileFormatException(getFileFormat());

		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(file);
		Element root = document.getRootElement();
		Element configuration = root.getChild("configuration");

		if (configuration != null) {

			String delayText = configuration.getChildText("delay");
			if (delayText == null)
				throw new AttributeNotFoundException("delay");

			String iterationsText = configuration.getChildText("iterations");
			if (iterationsText == null)
				throw new AttributeNotFoundException("iterations");

			String agentsText = configuration.getChildText("agents");
			if (agentsText == null)
				throw new AttributeNotFoundException("agents");

			String latticeSizeText = configuration.getChildText("latticeSize");
			if (latticeSizeText == null)
				throw new AttributeNotFoundException("latticeSize");

			String descriptionText = configuration.getChildText("description");
			if (descriptionText == null)
				throw new AttributeNotFoundException("description");

			int delay = Integer.parseInt(delayText);
			int iterations = Integer.parseInt(iterationsText);
			int agents = Integer.parseInt(agentsText);
			int latticeSize = Integer.parseInt(latticeSizeText);
			this.configuration = new Configuration(delay, iterations, agents, latticeSize, descriptionText);
		}

		Element initialCell = root.getChild("initialCell");

		if (initialCell != null) {

			String xText = initialCell.getChildText("x");
			if (xText == null)
				throw new AttributeNotFoundException("x");

			String yText = initialCell.getChildText("y");
			if (yText == null)
				throw new AttributeNotFoundException("y");

			String zText = initialCell.getChildText("z");
			if (zText == null)
				throw new AttributeNotFoundException("z");

			String stateText = initialCell.getChildText("state");
			if (stateText == null)
				throw new AttributeNotFoundException("state");

			Element colorElement = initialCell.getChild("color");
			if (colorElement == null)
				throw new AttributeNotFoundException("color");

			String rText = colorElement.getChildText("r");
			if (rText == null)
				throw new AttributeNotFoundException("r");

			String gText = colorElement.getChildText("g");
			if (gText == null)
				throw new AttributeNotFoundException("g");

			String bText = colorElement.getChildText("b");
			if (bText == null)
				throw new AttributeNotFoundException("b");

			int x = Integer.parseInt(xText);
			int y = Integer.parseInt(yText);
			int z = Integer.parseInt(zText);
			int state = Integer.parseInt(stateText);
			Color color = new Color(Integer.parseInt(rText), Integer.parseInt(gText), Integer.parseInt(bText));
			this.initialCell = new Cell(x, y, z, state, color);
		}

		Element tagRules = root.getChild("rules");
		rules = new ArrayList<Rule>();
		
		if (tagRules != null) {
			List<Element> elementRules = tagRules.getChildren("rule");

			if (elementRules.size() > 0) {
				

				for (Element rule : elementRules) {

					String neighbourhood = rule.getChildText("neighbourhood");
					if (neighbourhood == null)
						throw new AttributeNotFoundException("neighbourhood");

					String stateText = rule.getChildText("state");
					if (stateText == null)
						throw new AttributeNotFoundException("state");

					Element colorElement = rule.getChild("color");
					if (colorElement == null)
						throw new AttributeNotFoundException("color");

					String rText = colorElement.getChildText("r");
					if (rText == null)
						throw new AttributeNotFoundException("r");

					String gText = colorElement.getChildText("g");
					if (gText == null)
						throw new AttributeNotFoundException("g");

					String bText = colorElement.getChildText("b");
					if (bText == null)
						throw new AttributeNotFoundException("b");

					int state = Integer.parseInt(stateText);
					Color color = new Color(Integer.parseInt(rText), Integer.parseInt(gText), Integer.parseInt(bText));

					rules.add(new Rule(neighbourhood, state, color));
				}
			}
		}

	}

}
