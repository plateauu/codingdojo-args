package tech.plateauu.validator.definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//TODO Need a test
public class JsonDefinitionParser implements DefinitionParser {

	private static final Logger LOG = LoggerFactory.getLogger(JsonDefinitionParser.class);

	private static final String FILE_NAME = "definition.json";

	@Override
	public Definition parse() {
		InputStream stream = readFile();
		Definition definition = null;
		try {
			var mappedDefinition = parseMappedDefinition(stream);
			definition = new Definition(mappedDefinition);
		} catch (IOException e) {
			LOG.error("Exception during mapping", e);
		}
		return definition;
	}

	@Nullable
	private List<MappedDefinition> parseMappedDefinition(InputStream stream) throws IOException {
		var mapper = new ObjectMapper();
		var typeFactory = mapper.getTypeFactory();
		CollectionType collectionType = typeFactory.constructCollectionType(List.class, MappedDefinition.class);
		return mapper.readValue(stream, collectionType);
	}

	@Nullable
	private InputStream readFile() {
		var stream = JsonDefinitionParser.class.getClassLoader().getResourceAsStream(FILE_NAME);
		if (stream != null) {
			LOG.info("Found a JSON definition at file: " + FILE_NAME);
		}
		return stream;
	}

}
