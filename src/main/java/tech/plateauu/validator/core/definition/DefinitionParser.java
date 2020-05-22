package tech.plateauu.validator.core.definition;

import tech.plateauu.validator.core.ParserType;

import java.util.Optional;

public interface DefinitionParser {

	Optional<Definition> parse();

	ParserType type();
}
