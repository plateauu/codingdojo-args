package tech.plateauu.validator.core;

import tech.plateauu.validator.core.definition.Definition;
import tech.plateauu.validator.core.definition.JsonDefinitionParser;

public class SchemaDefinitionLoader {

	//TODO MM: load from different sources
	public Definition load() {
		return new JsonDefinitionParser().parse();
	}
}
