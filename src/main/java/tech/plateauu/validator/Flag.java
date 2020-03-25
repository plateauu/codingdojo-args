package tech.plateauu.validator;

import tech.plateauu.validator.SimpleFlag.ValidateOperandResult;

import java.util.List;

public interface Flag {

	SimpleFlag.FlagPair parse(String[] args);

	List<ValidateOperandResult> validate(List<SimpleFlag.FlagPair> args);
}
