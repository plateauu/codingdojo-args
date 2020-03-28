package tech.plateauu.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TODO Need to be rethinking this
public class SimpleFlagDefinition implements FlagDefinition {

	private final String name;
	private final ArgumentType type;

	public SimpleFlagDefinition(
			String name,
			ArgumentType type
	) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Flag parse(String[] inputs) {
		var args = Arrays.stream(inputs)
						 .collect(Collectors.toUnmodifiableList());

		if (!isPresent(args)) {
			return null;
		}

		var idx = args.indexOf(name);
		return switch (type) {
			case DUAL -> Flag.dual(name, args.get(++idx));
			case SINGLE -> Flag.single(name);
		};
	}

	@Override
	public List<ValidateOperandResult> validate(List<Flag> flags) {
		return flags.stream()
					.map(this::validateOperand)
					.collect(Collectors.toList());
	}

	private ValidateOperandResult validateOperand(Flag flag) {
		if (type.operand && flag.validateOperand()) {
			return new ValidateOperandResult(flag);
		}
		throw new RuntimeException("Field " + name + " has is operand flag but operand is [ " + flag.operand + "]");
	}

	private boolean isPresent(List<String> args) {
		return args.contains(name);
	}

	static class Flag {
		String flag;
		String operand;

		private Flag(String flag, String operand) {
			this.flag = flag;
			this.operand = operand;
		}

		private static Flag single(String name) {
			return new Flag(name, null);
		}

		private static Flag dual(String name, String operand) {
			return new Flag(name, operand);
		}

		private boolean validateOperand() {
			return flag != null && operand != null;
		}
	}

	static class ValidateOperandResult {
		private final Flag flag;
		private final Result validate;

		private ValidateOperandResult(Flag flag) {
			this.validate = Result.OK;
			this.flag = flag;
		}

		private enum Result {
			OK, ERROR
		}
	}
}
