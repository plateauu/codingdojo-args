package tech.plateauu.validator.core;

import tech.plateauu.validator.core.flag.Flag;

import java.util.List;
import java.util.stream.Collectors;

class SimpleFlagSchemaValidator implements FlagSchemaValidator {

	@Override
	public List<ValidateOperandResult> validate(List<Flag> flags) {
		return flags.stream()
				.map(this::validateOperand)
				.collect(Collectors.toList());
	}

	private ValidateOperandResult validateOperand(Flag flag) {
		flag.validate();
		return new ValidateOperandResult(flag);
	}
}
