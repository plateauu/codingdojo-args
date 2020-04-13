package tech.plateauu.validator.core.definition;

import tech.plateauu.validator.core.flag.Flag;
import tech.plateauu.validator.core.flag.FlagDefinition;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Definition {

	public final List<FlagDefinition> definitions;

	public Definition(List<FlagDefinition> definitions) {
		this.definitions = definitions;
	}

	public List<Flag> parse(String[] inputs) {
		var args = Arrays.stream(inputs)
				.collect(Collectors.toUnmodifiableList());

		return definitions.stream()
				.map(d -> d.parse(args))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toUnmodifiableList());
	}
}
