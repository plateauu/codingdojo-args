package tech.plateauu.validator.definition

import spock.lang.Specification

class SimpleFlagDefinitionTest extends Specification {

    def "Should parse flag of dual type with operand"() {
        given:
        def definition = MappedDefinition.of("-d", true, true)
        def expectedOperand = '/dev/null'
        def expectedFlag = '-d'
        def flags = [expectedFlag, expectedOperand]

        when:
        def result = definition.parse(flags)

        then: 'Flag should be present'
        result.present

        and:
        def flag = result.get()
        flag.name == expectedFlag
        flag.operand == expectedOperand
    }

    def "Should parse flag of single type without operand"() {
        given:
        def definition = MappedDefinition.of("-d", false, true)
        def expectedOperand = null
        def expectedFlag = '-d'
        def flags = [expectedFlag, '/dev/null']

        when:
        def result = definition.parse(flags)

        then: 'Flag should be present'
        result.present

        and:
        def flag = result.get()
        flag.name == expectedFlag
        flag.operand == expectedOperand
    }

    def "Should return nothing when input not contains flag"() {
        given:
        def definition = MappedDefinition.of("-d", false, true)
        def flags = ['-f', '/dev/null']

        expect:
        !definition.parse(flags).present
    }

}
