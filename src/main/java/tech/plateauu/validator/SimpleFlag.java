package tech.plateauu.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleFlag implements Flag {

	private final String name;
	private final ArgType type;
	private final boolean optional;

	public SimpleFlag(
			String name,
			ArgType type,
			boolean optional
	) {
		this.name = name;
		this.type = type;
		this.optional = optional;
	}

	@Override
	public boolean validate(String[] args) {
		var argList = Arrays.stream(args)
		                    .collect(Collectors.toUnmodifiableList());

		return isPresent(argList) && hasOperand(argList)  || optional;
	}

	private boolean hasOperand(List<String> list) {
		return type.operand && checkOperand(list);
	}

	private boolean checkOperand(List<String> list) {
		return false;
	}

	private boolean isPresent(List<String> args) {
		return args.contains("-" + name);
	}
}
