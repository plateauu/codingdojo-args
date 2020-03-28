package tech.plateauu.validator;

import tech.plateauu.validator.SimpleFlag.ValidateOperandResult;

import java.util.List;

public interface Flag {

	SimpleFlag.Argument parse(String[] args);

	List<ValidateOperandResult> validate(List<SimpleFlag.Argument> args);
}
