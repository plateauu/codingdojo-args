package tech.plateauu;

import tech.plateauu.validator.core.ArgumentSchemaValidator;
import tech.plateauu.validator.core.ValidateOperandResult;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		var schemaValidator = new ArgumentSchemaValidator();
		List<ValidateOperandResult> results = schemaValidator.validate(args);
		System.out.println(results);
	}
}
