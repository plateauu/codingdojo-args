package tech.plateauu.validator.core.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Optional;

public class JsonDefinitionParser implements DefinitionParser {

	private static final Logger LOG = LoggerFactory.getLogger(JsonDefinitionParser.class);

	private final String fileName;

	public JsonDefinitionParser(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Optional<Definition> parse() {
		return readFile()
				.map(JsonDefinitionParserUtil::parse);
	}


	private Optional<File> readFile() {
		return Optional.ofNullable(JsonDefinitionParser.class.getClassLoader().getResource(fileName))
				.map(r -> {
					var file = new File(r.getPath());
					LOG.info("Found a definition at JSON file: " + file.getAbsolutePath());
					return file;
				});
	}

}
