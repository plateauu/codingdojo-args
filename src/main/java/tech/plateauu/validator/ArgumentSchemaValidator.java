package tech.plateauu.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ArgumentSchemaValidator {

	private static Logger log = LoggerFactory.getLogger(
			ArgumentSchemaValidator.class.getName()
	);

	public ArgumentSchemaValidator() {
		loadSchema();
	}

	private void loadSchema() {
		//do nothing
	}

	public void validate(String[] arguments) {
		log.info("Arguments passed:");
		AtomicInteger counter = new AtomicInteger(1);
		Arrays.stream(arguments)
			  .forEach(a -> log.info("Argument[{}]: {}", counter.getAndIncrement(), a));
	}
}
