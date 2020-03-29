package tech.plateauu.validator;

class ValidateOperandResult {
	private final Flag flag;
	private final Result validate;

	ValidateOperandResult(Flag flag) {
		this.validate = Result.OK;
		this.flag = flag;
	}

	enum Result {
		OK, ERROR
	}
}
