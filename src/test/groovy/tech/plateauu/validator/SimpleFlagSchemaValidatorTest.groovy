package tech.plateauu.validator

import spock.lang.Specification

class SimpleFlagSchemaValidatorTest extends Specification {

    def "should validate single flag with success"() {
        given:
        def flag = Flag.single('-d')

    }

}
