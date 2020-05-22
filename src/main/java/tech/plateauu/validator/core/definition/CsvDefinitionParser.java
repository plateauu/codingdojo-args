package tech.plateauu.validator.core.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.plateauu.validator.core.ParserType;

import java.io.File;
import java.util.Optional;

public class CsvDefinitionParser implements DefinitionParser {

	private static final Logger LOG = LoggerFactory.getLogger(CsvDefinitionParser.class);

	private final String fileName;

	public CsvDefinitionParser(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Optional<Definition> parse() {
		return readFile().map(CsvDefinitionParserUtil::parse);
	}

	@Override
	public ParserType type() {
		return ParserType.CSV;
	}

	private Optional<File> readFile() {
		return Optional.ofNullable(JsonDefinitionParser.class.getClassLoader().getResource(fileName))
				.map(r -> {
					File file = new File(r.getPath());
					LOG.info("Found a definition at CSV file: " + file.getAbsolutePath());
					return file;
				});
	}
}
