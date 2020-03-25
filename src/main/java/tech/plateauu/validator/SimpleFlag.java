package tech.plateauu.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//TODO Need to be rethinking
public class SimpleFlag implements Flag {

	private final String name;
	private final ArgType type;
	private final boolean optional;

	public SimpleFlag(
			String name,
			ArgType type,
			boolean optional
	) {
		this.name = name;
		this.type = type;
		this.optional = optional;
	}

	@Override
	public FlagPair parse(String[] args) {
		var argList = Arrays.stream(args)
							.collect(Collectors.toUnmodifiableList());

		if (!isPresent(argList)) {
			return null;
		}

		var flagIndex = argList.indexOf("-" + name);
		return switch (type) {
			case ARGUMENT -> FlagPair.operand(name, argList.get(flagIndex + 1));
			case FLAG -> FlagPair.flag(name);
		};
	}

	public List<ValidateOperandResult> validate(List<FlagPair> pairs) {
		return pairs.stream()
					.map(this::validateOperand)
					.collect(Collectors.toList());
	}

	private ValidateOperandResult validateOperand(FlagPair pair) {
		if (type.operand && pair.validateOperand()) {
			return new ValidateOperandResult(pair);
		}
		throw new RuntimeException("Field " + name + " has is operand flag but operand is [ " + pair.operand + "]");
	}

	private boolean isPresent(List<String> args) {
		return args.contains("-" + name);
	}

	static class FlagPair {
		String flag;
		String operand;

		private FlagPair(String flag, String operand) {
			this.flag = flag;
			this.operand = operand;
		}

		private static FlagPair flag(String name) {
			return new FlagPair(name, null);
		}

		private static FlagPair operand(String name, String operand) {
			return new FlagPair(name, operand);
		}

		private boolean validateOperand() {
			return flag != null && operand != null;
		}
	}

	static class ValidateOperandResult {
		private enum Result {
			OK, ERROR
		}

		private ValidateOperandResult(FlagPair pair) {
			this.validate = Result.OK;
			this.pair = pair;
		}

		private final Result validate;
		private final FlagPair pair;
	}
}
