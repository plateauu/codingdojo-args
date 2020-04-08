package tech.plateauu.validator.definition;

import tech.plateauu.validator.Flag;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Definition {

	public final List<MappedDefinition> definitions;

	public Definition(List<MappedDefinition> definitions) {
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
