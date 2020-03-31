package tech.plateauu.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArgumentSchemaValidator {

	private static Logger log = LoggerFactory.getLogger(ArgumentSchemaValidator.class);

	private final FlagDefinition schema;

	public ArgumentSchemaValidator() {
		this.schema = loadSchema();
	}

	private FlagDefinition loadSchema() {
		return new SimpleFlagDefinition("-d", ArgumentType.DUAL);
	}

	public List<ValidateOperandResult> validate(String[] arguments) {
		var flag = schema.parse(arguments);

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
