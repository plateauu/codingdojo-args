package tech.plateauu.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SimpleFlagDefinition implements FlagDefinition {

	private final Logger log = LoggerFactory.getLogger(SimpleFlagDefinition.class);

	private final String name;
	private final ArgumentType type;

	SimpleFlagDefinition(String name, ArgumentType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Flag parse(String[] inputs) {
		log.info("Started parsing arguments for flag name {}: {}", name, String.join(" ", inputs));
		var args = Arrays.stream(inputs)
						 .collect(Collectors.toUnmodifiableList());

		if (!isPresent(args)) {
			log.info("Nothing has been parsed");
			return null;
		}

		var idx = args.indexOf(name);
		return switch (type) {
			case DUAL -> getDualFlag(args, idx);
			case SINGLE -> getSingleFlag();
		};
	}

	private Flag getDualFlag(List<String> args, int idx) {
		var operand = getOperand(args, idx);
		var dual = Flag.dual(name, operand);
		log.info("Found flag: {} and its operand: {}", name, operand);
		return dual;
	}

	private Flag getSingleFlag() {
		log.info("Found single flag for definition {}", name);
		return Flag.single(name);
	}

	private String getOperand(List<String> args, int flagIndex) {
		var operand = args.get(++flagIndex);
		if (operand.startsWith("-")) {
			throw new RuntimeException("There should be a operand after " + name + " flag");
		}
		return operand;
	}

	private boolean isPresent(List<String> args) {
		return args.contains(name);
	}
}
