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

    def "Should validate dual flag with success"() {
        given:
        def flag = Flag.dual('-d', '/dev/null')
        def validator = new SimpleFlagSchemaValidator()
        def input = [flag]

        when:
        def result = validator.validate(input).first()

        then:
        result == new ValidateOperandResult(flag)

        and:
        noExceptionThrown()
    }

    def "Should not pass validation for dual flag"() {
        given:
        def flag = Flag.dual('-d', null)
        def validator = new SimpleFlagSchemaValidator()
        def input = [flag]

        when:
        validator.validate(input).first()

        then:
        thrown(RuntimeException)
    }


}
