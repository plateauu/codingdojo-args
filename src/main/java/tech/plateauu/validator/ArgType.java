package tech.plateauu.validator;

enum ArgType {
	FLAG(false), ARGUMENT(true);

	final boolean operand;

	ArgType(boolean operand) {
		this.operand = operand;
	}
}
