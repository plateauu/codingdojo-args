package tech.plateauu.validator.core

import spock.lang.Specification
import tech.plateauu.validator.core.definition.JsonDefinitionParser

class SchemaDefinitionLoaderTest extends Specification {

    def "Should load definitions from json schema"() {
        def loader = new SchemaDefinitionLoader()
        loader.parsers[SchemaDefinitionLoader.ParserType.JSON] = new JsonDefinitionParser("testDefinition.json")

        when:
        def definition = loader.load()

        then:
        with(definition.definitions[2]) {
            it.name == 'json'
        }
    }

    def "Should load definitions from csv schema"() {
        def loader = new SchemaDefinitionLoader()
        loader.parsers[SchemaDefinitionLoader.ParserType.CSV] = new JsonDefinitionParser("testDefinition.csv")

        when:
        def definition = loader.load()

        then:
        definition.definitions.last().name == 'csv'
    }

    def "Should throw exception when schema could not be find"() {}

}
