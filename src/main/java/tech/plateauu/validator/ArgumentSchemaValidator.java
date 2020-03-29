package tech.plateauu.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArgumentSchemaValidator {

	private static Logger log = LoggerFactory.getLogger(
			ArgumentSchemaValidator.class.getName()
	);

	public ArgumentSchemaValidator() {
		loadSchema();
	}

	private void loadSchema() {
		//do nothing
	}

	public List<ValidateOperandResult> validate(String[] arguments) {
		var definition = new SimpleFlagDefinition("-d", ArgumentType.DUAL);
		var flag = definition.parse(arguments);
		if (flag == null) {
			return List.of(ValidateOperandResult.error(flag));
		}
		var validator = new SimpleFlagSchemaValidator();
		return validator.validate(List.of(flag))
						.stream()
						.filter(Objects::nonNull)
						.collect(Collectors.toUnmodifiableList());
	}
}
