package tech.plateauu.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArgumentSchemaValidatorTest {

	@Test
	@DisplayName("Should log something")
	void shouldLogSomething() {
		var validator = new ArgumentSchemaValidator();
		validator.validate(new String[] { "dupa", "blada" });
	}

}