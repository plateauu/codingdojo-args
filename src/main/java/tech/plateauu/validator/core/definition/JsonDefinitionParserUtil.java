package tech.plateauu.validator.core.definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import tech.plateauu.validator.core.flag.FlagDefinition;
import tech.plateauu.validator.core.flag.MappedDefinition;

import java.io.File;
import java.io.IOException;
import java.util.List;

class JsonDefinitionParserUtil {

	static Definition parse(File file) {
		try {
			var mappedDefinition = parseMappedDefinition(file);
			return new Definition(mappedDefinition);
		} catch (IOException e) {
			throw new RuntimeException("Exception during mapping", e);
		}
	}

	private static List<FlagDefinition> parseMappedDefinition(File file) throws IOException {
		var mapper = new ObjectMapper();
		var typeFactory = mapper.getTypeFactory();
		var collectionType = typeFactory.constructCollectionType(List.class, MappedDefinition.class);
		return mapper.readValue(file, collectionType);
	}
}
