package tech.plateauu.validator;

public enum ArgumentType {
	SINGLE(false),
	//TODO Need to change name
	DUAL(true);

	final boolean operand;

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
