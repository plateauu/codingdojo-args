package tech.plateauu.validator.core.flag;

import java.util.List;
import java.util.Optional;

public interface FlagDefinition {

	Optional<Flag> parse(List<String> args);

}
