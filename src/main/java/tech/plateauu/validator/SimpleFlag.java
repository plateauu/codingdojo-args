package tech.plateauu.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TODO Need to be rethinking this
public class SimpleFlag implements Flag {

	private final String name;
	private final ArgType type;

	public SimpleFlag(
			String name,
			ArgType type
	) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Argument parse(String[] args) {
		var argList = Arrays.stream(args)
							.collect(Collectors.toUnmodifiableList());

		if (!isPresent(argList)) {
			return null;
		}

		var idx = argList.indexOf(name);
		return switch (type) {
			case ARGUMENT -> Argument.operand(name, argList.get(idx + 1));
			case FLAG -> Argument.flag(name);
		};
	}

	@Override
	public List<ValidateOperandResult> validate(List<Argument> pairs) {
		return pairs.stream()
					.map(this::validateOperand)
					.collect(Collectors.toList());
	}

	private ValidateOperandResult validateOperand(Argument pair) {
		if (type.operand && pair.validateOperand()) {
			return new ValidateOperandResult(pair);
		}
		throw new RuntimeException("Field " + name + " has is operand flag but operand is [ " + pair.operand + "]");
	}

	private boolean isPresent(List<String> args) {
		return args.contains(name);
	}

	static class Argument {
		String flag;
		String operand;

		private Argument(String flag, String operand) {
			this.flag = flag;
			this.operand = operand;
		}

		private static Argument flag(String name) {
			return new Argument(name, null);
		}

		private static Argument operand(String name, String operand) {
			return new Argument(name, operand);
		}

		private boolean validateOperand() {
			return flag != null && operand != null;
		}
	}

	static class ValidateOperandResult {
		private enum Result {
			OK, ERROR
		}

		private final Argument argument;

		private final Result validate;

		private ValidateOperandResult(Argument argument) {
			this.validate = Result.OK;
			this.argument = argument;
		}
	}
}
