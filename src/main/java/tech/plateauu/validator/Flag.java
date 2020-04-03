package tech.plateauu.validator;

import java.util.Objects;

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

	//TODO This exception shuold be different
	void validate() {
		if (type.isSingleType() && name != null) {
			return;
		}
		if (type.isNotSingle() && name != null && operand != null) {
			return;
		}
		throw new RuntimeException("Field " + name + " has is operand flag but operand is [ " + operand + "]");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flag flag = (Flag) o;
		return Objects.equals(name, flag.name) &&
				Objects.equals(operand, flag.operand) &&
				type == flag.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, operand, type);
	}
}
