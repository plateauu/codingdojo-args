package tech.plateauu.validator

import spock.lang.Specification

class SimpleFlagSchemaValidatorTest extends Specification {

    def "Should validate single flag with success"() {
        given:
        def flag = Flag.single('-d')
        def validator = new SimpleFlagSchemaValidator()
        def input = [flag]

        when:
        def result = validator.validate(input).first()

        then:
        result == new ValidateOperandResult(flag)

        and:
        noExceptionThrown()
    }

}
