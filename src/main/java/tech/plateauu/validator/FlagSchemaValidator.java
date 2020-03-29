package tech.plateauu.validator;

import java.util.List;

interface FlagSchemaValidator {

	List<ValidateOperandResult> validate(List<Flag> args);

}
