package tech.plateauu.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleFlagTest {

	@Test
	@DisplayName("Should parse pair without exception")
	void shouldParseFlagPairWithoutException() {
		//given
		var f = new SimpleFlag("f", ArgType.ARGUMENT, false);

	}
}