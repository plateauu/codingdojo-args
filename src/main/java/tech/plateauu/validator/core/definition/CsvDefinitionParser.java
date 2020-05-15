package tech.plateauu.validator.core.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class CsvDefinitionParser implements DefinitionParser {

	private static final Logger LOG = LoggerFactory.getLogger(CsvDefinitionParser.class);

	private static final String FILE_NAME = "definition.csv";

	@Override
	public Definition parse() {
		var file = readFile();
		return CsvDefinitionParserUtil.parse(file);
	}

	private File readFile() {
		URL resource = JsonDefinitionParser.class.getClassLoader().getResource(FILE_NAME);
		Objects.requireNonNull(resource);
		File file = new File(resource.getPath());
		LOG.info("Found a JSON definition at file: " + file.getAbsolutePath());
		return file;
	}
}
