package tech.plateauu;

import tech.plateauu.validator.ArgumentSchemaValidator;

public class Main {

	public static void main(String[] args) {
		var schemaValidator = new ArgumentSchemaValidator();
		schemaValidator.validate(args);
	}
}
