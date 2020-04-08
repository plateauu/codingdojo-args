package tech.plateauu.validator.core.flag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.plateauu.validator.core.definition.ArgumentType;

import java.util.List;
import java.util.Optional;

public class MappedDefinition implements FlagDefinition {

	private final Logger log = LoggerFactory.getLogger(MappedDefinition.class);

	private final String name;
	private final boolean operand;
	private final boolean optional;
	private final ArgumentType type;

	private MappedDefinition(String name, boolean operand, boolean optional, ArgumentType type) {
		this.name = name;
		this.operand = operand;
		this.optional = optional;
		this.type = type;
	}

	@JsonCreator
	public static MappedDefinition of(
			@JsonProperty("name") String name,
			@JsonProperty("operand") boolean operand,
			@JsonProperty("optional") boolean optional
	) {
		var type = operand ? ArgumentType.DUAL : ArgumentType.SINGLE;
		return new MappedDefinition(name, operand, optional, type);
	}

	@Override
	public Optional<Flag> parse(List<String> inputs) {
		if (!isPresent(inputs)) {
			log.info("Nothing has been parsed. Flag " + name + " is not passed");
			return Optional.empty();
		}

		var instrString = String.join(" ", inputs);
		log.info("Started parsing arguments for flag name {}: {}", name, instrString);

		var idx = inputs.indexOf(name);
		Flag flag = switch (type) {
			case DUAL -> getDualFlag(inputs, idx);
			case SINGLE -> getSingleFlag();
		};

		return Optional.of(flag);
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

	//TODO MM: Need to change fail path
	private String getOperand(List<String> args, int flagIndex) {
		var operand = args.get(++flagIndex);
		if (operand.startsWith("-")) {
			throw new RuntimeException("There should be an operand after " + name + " flag");
		}
		return operand;
	}

	private boolean isPresent(List<String> args) {
		return args.contains(name);
	}
}
