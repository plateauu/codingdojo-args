package tech.plateauu.validator.core.definition

import spock.lang.Specification
import tech.plateauu.validator.core.flag.MappedDefinition

class CsvDefinitionParserUtilTest extends Specification {

    def 'Should parse definition without errors'() {
        given:
        def resource = CsvDefinitionParserUtilTest.class.getClassLoader().getResource("testDefinition.csv")

        when:
        def definition = CsvDefinitionParserUtil.parse(new File(resource.toURI()))

        then:
        definition

        and:
        definition.definitions.size() == 3

        and:
        Map<String, MappedDefinition> entries = definition.definitions.collectEntries { [(it.name): it] }
        with(entries["-d"]) {
            operand
            optional
        }

        with(entries["-f"]) {
            operand
            !optional
        }
        with(entries["csv"]) {
            operand
            !optional
        }
    }

    def 'should throw exception and stop program when file is corrupted'() {
        def resource = CsvDefinitionParserUtilTest.class.getClassLoader().getResource("testDefinitionCorrupted.csv")

        when:
        JsonDefinitionParserUtil.parse(new File(resource.toURI()))

        then:
        thrown RuntimeException
    }
}
