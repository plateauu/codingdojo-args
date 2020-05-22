package tech.plateauu.validator.core;

import org.jetbrains.annotations.NotNull;
import tech.plateauu.validator.core.definition.CsvDefinitionParser;
import tech.plateauu.validator.core.definition.Definition;
import tech.plateauu.validator.core.definition.DefinitionParser;
import tech.plateauu.validator.core.definition.JsonDefinitionParser;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SchemaDefinitionLoader {

	//TODO MM: Add tests
	private final Map<ParserType, DefinitionParser> parsers;

	public SchemaDefinitionLoader() {
		this.parsers = init();
	}

	@NotNull
	private LinkedHashMap<ParserType, DefinitionParser> init() {
		return Map.of(
				ParserType.CSV, new CsvDefinitionParser("definition.csv"),
				ParserType.JSON, new JsonDefinitionParser("definition.json")
		).entrySet()
				.stream()
				.sorted(Comparator.comparing(e -> e.getKey().order, Comparator.naturalOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
	}

	public Definition load() {
		for (Map.Entry<ParserType, DefinitionParser> entry : parsers.entrySet()) {
			var definition = entry.getValue().parse();
			if (definition.isPresent()) {
				return definition.get();
			}
		}
		throw new RuntimeException("Unable to find proper definition file");
	}

	public enum ParserType {
		JSON(0),
		CSV(1);

		private final int order;

		ParserType(int order) {
			this.order = order;
		}
	}

}
