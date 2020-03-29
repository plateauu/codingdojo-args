package tech.plateauu.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SimpleFlagDefinition implements FlagDefinition {

	private final String name;
	private final ArgumentType type;

	SimpleFlagDefinition(String name, ArgumentType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Flag parse(String[] inputs) {
		var args = Arrays.stream(inputs)
						 .collect(Collectors.toUnmodifiableList());

		if (!isPresent(args)) {
			return null;
		}

		var idx = args.indexOf(name);
		return switch (type) {
			case DUAL -> Flag.dual(name, args.get(++idx));
			case SINGLE -> Flag.single(name);
		};
	}

	private boolean isPresent(List<String> args) {
		return args.contains(name);
	}
}
