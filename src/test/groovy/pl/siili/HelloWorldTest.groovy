package pl.siili

import spock.lang.Specification

/**
 * @author Tomasz Bialecki
 */
class HelloWorldTest extends Specification {

    def "true should be equal true"() {
        expect:
            true
    }

    def "'given' block should be defined with 'then' block"() {
        given:
            def i = 0;

        when:
            i++;

        then:
            i == 1
    }

    def "failing tests should give reasonable output message"() {
        when:
            def list = [[1, 2, 3], [2, 3, 4]];

        then:
            list.get(0).sum() == 7

    }

    def "failing tests should give reasonable output message2"() {
        expect:
            "label" == "lable"
    }

    def "test structure should be even mode meaningful and readable"() {
        given: "setup block can contain message"
            //code
        and: "more parts"
            //
        when: "some comment"

        then: "some comment"

    }

    def "++i should iterate"() {
        given:
            def i = 0
        when:
            i = ++i
        then:
            i == 1
    }

    def "++i should iterate2"() {
        given: "i variable"
            def i = 0
        when: "using ++ operator"
            i = ++i
        then: "should be equal 1"
            i == 1
    }

    def "++i should iterate3"() {
        given:
            def i = 0
        expect:
            ++i == 1
    }
}
