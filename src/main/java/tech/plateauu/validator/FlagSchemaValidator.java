package tech.plateauu.validator;

import java.util.List;

public interface FlagSchemaValidator {

	List<ValidateOperandResult> validate(List<Flag> args);

}
