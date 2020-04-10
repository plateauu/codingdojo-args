package tech.plateauu.validator.core.definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.plateauu.validator.core.flag.FlagDefinition;
import tech.plateauu.validator.core.flag.MappedDefinition;

import java.io.File;
import java.io.IOException;
import java.util.List;

class JsonDefinitionParserUtil {

	private static final Logger LOG = LoggerFactory.getLogger(JsonDefinitionParserUtil.class);

	public static Definition parse(File file) {
		Definition definition = null;
		try {
			var mappedDefinition = parseMappedDefinition(file);
			definition = new Definition(mappedDefinition);
		} catch (IOException e) {
			throw new RuntimeException("Exception during mapping", e);
		}
		return definition;
	}

	@Nullable
	private static List<FlagDefinition> parseMappedDefinition(File file) throws IOException {
		var mapper = new ObjectMapper();
		var typeFactory = mapper.getTypeFactory();
		CollectionType collectionType = typeFactory.constructCollectionType(List.class, MappedDefinition.class);
		return mapper.readValue(file, collectionType);
	}
}
