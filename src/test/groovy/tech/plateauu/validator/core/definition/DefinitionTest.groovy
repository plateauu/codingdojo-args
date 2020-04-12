package tech.plateauu.validator.core.definition

import spock.lang.Specification
import tech.plateauu.validator.core.flag.MappedDefinition

class DefinitionTest extends Specification {

    def "Should parse flag from parsed definition"() {
        def mappedDefinition = MappedDefinition.of("-d", false, true)
        def definition = new Definition([mappedDefinition])

        when:
        definition.parse(["-d"] as String[])

        then:
        noExceptionThrown()
    }
}