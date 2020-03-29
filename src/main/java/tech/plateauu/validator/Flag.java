package tech.plateauu.validator;

class Flag {
	private final String name;
	private final String operand;
	private final ArgumentType type;

	private Flag(String name, String operand, ArgumentType type) {
		this.name = name;
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
		if (type.isSingleType() && name != null) {
			return;
		}
		if (type.isNotSingle() && name != null && operand != null) {
			return;
		}
		throw new RuntimeException("Field " + name + " has is operand flag but operand is [ " + operand + "]");
	}

}
