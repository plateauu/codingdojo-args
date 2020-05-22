package tech.plateauu.validator.core;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.plateauu.validator.core.definition.CsvDefinitionParser;
import tech.plateauu.validator.core.definition.Definition;
import tech.plateauu.validator.core.definition.DefinitionParser;
import tech.plateauu.validator.core.definition.JsonDefinitionParser;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

class SchemaDefinitionLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchemaDefinitionLoader.class);

	private final Map<ParserType, DefinitionParser> parsers;

	SchemaDefinitionLoader() {
		this.parsers = init();
	}

	@NotNull
	private LinkedHashMap<ParserType, DefinitionParser> init() {
		var csvParser = new CsvDefinitionParser("definition.csv");
		var jsonParser = new JsonDefinitionParser("definition.json");
		return Map.of(
				csvParser.type(), csvParser,
				jsonParser.type(), jsonParser
		).entrySet()
				.stream()
				.sorted(Comparator.comparing(e -> e.getKey().getOrder(), Comparator.naturalOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
	}

	Definition load() {
		for (Map.Entry<ParserType, DefinitionParser> entry : parsers.entrySet()) {
			var definition = entry.getValue().parse();
			if (definition.isPresent()) {
				LOGGER.debug("Found definition at {} parser", entry.getValue().type());
				return definition.get();
			}
		}
		throw new RuntimeException("Unable to find proper definition file");
	}

}
