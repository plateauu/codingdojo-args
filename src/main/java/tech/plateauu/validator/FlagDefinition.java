package tech.plateauu.validator;

import tech.plateauu.validator.SimpleFlagDefinition.ValidateOperandResult;

import java.util.List;

public interface FlagDefinition {

	SimpleFlagDefinition.Flag parse(String[] args);

	List<ValidateOperandResult> validate(List<SimpleFlagDefinition.Flag> args);
}
