package tech.plateauu.validator.core

import spock.lang.Specification
import tech.plateauu.validator.core.definition.CsvDefinitionParser
import tech.plateauu.validator.core.definition.JsonDefinitionParser

class SchemaDefinitionLoaderTest extends Specification {

    def "Should load definitions from json schema"() {
        given:
        def loader = new SchemaDefinitionLoader()
        loader.parsers[ParserType.JSON] = new JsonDefinitionParser("testDefinition.json")

        when:
        def definition = loader.load()

        then:
        with(definition.definitions[2]) {
            it.name == 'json'
        }
    }

    def "Should load definitions from csv schema"() {
        given:
        def loader = new SchemaDefinitionLoader()
        loader.parsers.remove(ParserType.JSON)
        loader.parsers[ParserType.CSV] = new CsvDefinitionParser("testDefinition.csv")

        when:
        def definition = loader.load()

        then:
        definition.definitions.last().name == 'csv'
    }

    def "Should throw exception when schema could not be find"() {
        given:
        def loader = new SchemaDefinitionLoader()
        loader.parsers.removeAll { it }

        when:
        loader.load()

        then:
        thrown RuntimeException
    }

}
