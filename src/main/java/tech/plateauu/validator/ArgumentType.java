package tech.plateauu.validator;

public enum ArgumentType {
	SINGLE(false),
	DUAL(true);

	private final boolean operand;

	ArgumentType(boolean operand) {
		this.operand = operand;
	}

	boolean isSingleType() {
		return this == SINGLE;
	}

	boolean isNotSingle() {
		return !isSingleType();
	}
}
