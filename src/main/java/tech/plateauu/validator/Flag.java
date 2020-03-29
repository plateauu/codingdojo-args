package tech.plateauu.validator;

class Flag {
	private final String flag;
	private final String operand;
	private final ArgumentType type;

	private Flag(String flag, String operand, ArgumentType type) {
		this.flag = flag;
		this.operand = operand;
		this.type = type;
	}

	static Flag single(String name) {
		return new Flag(name, null, ArgumentType.SINGLE);
	}

	static Flag dual(String name, String operand) {
		return new Flag(name, operand, ArgumentType.DUAL);
	}

	void validateOperand() {
		if (type.isSingleType() && flag != null) {
			return;
		}
		if (type.isNotSingle() && flag != null && operand != null) {
			return;
		}
		throw new RuntimeException("Field " + flag + " has is operand flag but operand is [ " + operand + "]");
	}

}
