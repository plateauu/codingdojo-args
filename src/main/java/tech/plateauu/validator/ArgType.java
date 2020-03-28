package tech.plateauu.validator;

public enum ArgType {
	FLAG(false),
	//TODO Need to change name
	ARGUMENT(true);

	final boolean operand;

	ArgType(boolean operand) {
		this.operand = operand;
	}
}
