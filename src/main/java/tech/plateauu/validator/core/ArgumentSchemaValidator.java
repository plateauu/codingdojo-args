package tech.plateauu.validator.core;

import tech.plateauu.validator.core.definition.Definition;
import tech.plateauu.validator.core.flag.Flag;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArgumentSchemaValidator {

	private final Definition schema;

	public ArgumentSchemaValidator() {
		this.schema = loadSchema();
	}

	private Definition loadSchema() {
		return new SchemaDefinitionLoader().load();
	}

	public List<ValidateOperandResult> validate(String[] arguments) {
		List<Flag> flag = schema.parse(arguments);

		if (flag.isEmpty()) {
			var error = ValidateOperandResult.error(null);
			return List.of(error);
		}

		return validate(flag);
	}

	private List<ValidateOperandResult> validate(List<Flag> flags) {
		var validator = new SimpleFlagSchemaValidator();
		return validator.validate(flags)
				.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.toUnmodifiableList());
	}
}
