package tech.plateauu.validator;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleFlagSchemaValidator implements FlagSchemaValidator {

	@Override
	public List<ValidateOperandResult> validate(List<Flag> flags) {
		return flags.stream()
					.map(this::validateOperand)
					.collect(Collectors.toList());
	}

	private ValidateOperandResult validateOperand(Flag flag) {
		flag.validateOperand();
		return new ValidateOperandResult(flag);
	}
}
