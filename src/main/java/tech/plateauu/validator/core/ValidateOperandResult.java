package tech.plateauu.validator.core;

import org.jetbrains.annotations.Nullable;
import tech.plateauu.validator.core.flag.Flag;

import java.util.Objects;

public class ValidateOperandResult {
	private final Flag flag;
	private final Result result;

	ValidateOperandResult(Flag flag) {
		this.result = Result.OK;
		this.flag = flag;
	}

	ValidateOperandResult(Flag flag, Result result) {
		this.result = result;
		this.flag = flag;
	}

	static ValidateOperandResult success(Flag flag) {
		return new ValidateOperandResult(flag, Result.OK);
	}

	static ValidateOperandResult error(@Nullable Flag flag) {
		return new ValidateOperandResult(flag, Result.ERROR);
	}

	enum Result {
		OK, ERROR
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ValidateOperandResult that = (ValidateOperandResult) o;
		return Objects.equals(flag, that.flag) &&
				result == that.result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flag, result);
	}

	@Override
	public String toString() {
		return "ValidateOperandResult[ flag=" + flag +
				", result=" + result + ']';
	}
}
