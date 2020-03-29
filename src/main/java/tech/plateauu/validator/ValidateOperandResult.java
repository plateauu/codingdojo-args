package tech.plateauu.validator;

class ValidateOperandResult {
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

	static ValidateOperandResult error(Flag flag) {
		return new ValidateOperandResult(flag, Result.ERROR);
	}

	enum Result {
		OK, ERROR
	}
}
