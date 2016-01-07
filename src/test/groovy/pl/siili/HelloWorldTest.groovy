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

    def "++i should iterate"() {
        given:
            def i = 0
        when:
            i = ++i
        then:
            i == 1
    }

}
