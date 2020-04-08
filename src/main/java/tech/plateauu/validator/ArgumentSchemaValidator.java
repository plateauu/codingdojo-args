package tech.plateauu.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.plateauu.validator.definition.Definition;
import tech.plateauu.validator.definition.JsonDefinitionParser;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArgumentSchemaValidator {

	private static Logger log = LoggerFactory.getLogger(ArgumentSchemaValidator.class);

	private final List<FlagDefinition> schema;

	public ArgumentSchemaValidator() {
		this.schema = loadSchema();
	}

	private List<FlagDefinition> loadSchema() {
		Definition parse = new JsonDefinitionParser().parse();
		var definition = new SimpleFlagDefinition("-d", ArgumentType.DUAL);
		return List.of(definition);
	}

	//MappedDefinition and flagDefinition are the same
	public List<ValidateOperandResult> validate(String[] arguments) {
		Flag flag = schema.get(0).parse(arguments);

		if (flag == null) {
			var error = ValidateOperandResult.error(null);
			return List.of(error);
		}

		return validate(flag);
	}

	private List<ValidateOperandResult> validate(Flag flag) {
		var validator = new SimpleFlagSchemaValidator();
		return validator.validate(List.of(flag))
						.stream()
						.filter(Objects::nonNull)
						.collect(Collectors.toUnmodifiableList());
	}
}
