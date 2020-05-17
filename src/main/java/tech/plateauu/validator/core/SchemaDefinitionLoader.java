package tech.plateauu.validator.core;

import tech.plateauu.validator.core.definition.CsvDefinitionParser;
import tech.plateauu.validator.core.definition.Definition;
import tech.plateauu.validator.core.definition.JsonDefinitionParser;

public class SchemaDefinitionLoader {

	//TODO MM: load from different sources
	//TODO MM: Add test
	public Definition load() {
		return new JsonDefinitionParser().parse()
				.or(() -> new CsvDefinitionParser().parse())
				.orElseThrow(() -> new RuntimeException("Unable to find proper definition file"));
	}
}
