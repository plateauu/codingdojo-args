package tech.plateauu.validator.core;

import tech.plateauu.validator.core.flag.Flag;

import java.util.List;

interface FlagSchemaValidator {

	List<ValidateOperandResult> validate(List<Flag> args);

}
