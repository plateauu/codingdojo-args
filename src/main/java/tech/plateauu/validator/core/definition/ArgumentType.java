package tech.plateauu.validator.core.definition;

public enum ArgumentType {
	SINGLE(false),
	DUAL(true);

	private final boolean operand;

	ArgumentType(boolean operand) {
		this.operand = operand;
	}

	public boolean isSingleType() {
		return this == SINGLE;
	}

	public boolean isNotSingle() {
		return !isSingleType();
	}
}
