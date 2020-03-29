package tech.plateauu.validator

import spock.lang.Specification

class SimpleFlagDefinitionTest extends Specification {

    def "Should parse flag of dual type with operand"() {
        given:
        def definition = new SimpleFlagDefinition("-d", ArgumentType.DUAL)
        def expectedOperand = '/dev/null'
        def expectedFlag = '-d'
        def flags = [expectedFlag, expectedOperand]

        when:
        def argument = definition.parse(flags as String[])

        then: 'Flag should not be null'
        argument

        and:
        argument.flag == expectedFlag
        argument.operand == expectedOperand
    }

    def "Should parse flag of single type without operand"() {
        given:
        def definition = new SimpleFlagDefinition("-d", ArgumentType.SINGLE)
        def expectedOperand = null
        def expectedFlag = '-d'
        def flags = [expectedFlag, '/dev/null']

        when:
        def result = definition.parse(flags as String[])

        then: 'Flag should not be null'
        result

        and:
        result.flag == expectedFlag
        result.operand == expectedOperand
    }

    def "Should return null when input not contains flag"() {
        given:
        def definition = new SimpleFlagDefinition("-d", ArgumentType.SINGLE)
        def flags = ['-f', '/dev/null']

        expect:
        !definition.parse(flags as String[])
    }

}
