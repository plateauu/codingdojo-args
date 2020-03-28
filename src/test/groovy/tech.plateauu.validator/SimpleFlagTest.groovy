package tech.plateauu.validator

import spock.lang.Specification

class SimpleFlagTest extends Specification {

    def "Should parse pair of argument type"() {
        given:
        def flag = new SimpleFlag("-d", ArgType.ARGUMENT, false)
        def expectedOperand = '/dev/null'
        def expectedFlag = '-d'
        def flags = [expectedFlag, expectedOperand]

        when:
        def result = flag.parse(flags as String[])

        then: 'Pair should not be null'
        result

        and:
        result.flag == expectedFlag
        result.operand == expectedOperand
    }

    def "Should parse pair of flag type"() {
        given:
        def flag = new SimpleFlag("-d", ArgType.FLAG, false)
        def expectedOperand = null
        def expectedFlag = '-d'
        def flags = [expectedFlag, '/dev/null']

        when:
        def result = flag.parse(flags as String[])

        then: 'Pair should not be null'
        result

        and:
        result.flag == expectedFlag
        result.operand == expectedOperand
    }

}
