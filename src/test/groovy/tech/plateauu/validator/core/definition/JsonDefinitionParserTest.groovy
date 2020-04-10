package tech.plateauu.validator.core.definition

import spock.lang.Specification
import tech.plateauu.validator.core.flag.MappedDefinition

class JsonDefinitionParserTest extends Specification {

    def 'Should parse definition without errors'() {
        when:
        def resource = JsonDefinitionParserTest.class.getClassLoader().getResource("testDefinition.json")
        def definition = JsonDefinitionParserUtil.parse(new File(resource.toURI()))

        then:
        definition

        and:
        definition.definitions.size() == 2

        and:
        Map<String, MappedDefinition> entries = definition.definitions.collectEntries { [(it.name): it] }
        with(entries["-d"]) {
            name == '-d'
            operand
            optional
        }

        with(entries["-f"]) {
            name == '-f'
            !operand
            !optional
        }
    }

    def "should throw exception and stop program when file is corrupted"() {
        def resource = JsonDefinitionParserTest.class.getClassLoader().getResource("testDefinitionCorrupted.json")

        when:
        JsonDefinitionParserUtil.parse(new File(resource.toURI()))

        then:
        thrown RuntimeException
    }
}
