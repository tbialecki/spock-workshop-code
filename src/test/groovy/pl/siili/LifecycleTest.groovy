package pl.siili

import spock.lang.Shared
import spock.lang.Specification

/**
 * @author Tomasz Bialecki
 */
class LifecycleTest extends Specification {

    class Simple {
        Simple() {
            println "=== constructing Simple object ==="
        }
    }

    @Shared
    def objectUnderTest = new Simple();


    def setup() {
        println "=== setup ==="
    }

    def cleanup() {
        println "=== cleanup ==="
    }

    def setupSpec() {
        println "=== setupSpec ==="
    }

    def cleanupSpec() {
        println "=== cleanupSpec ==="
    }

    def "should assert google"() {
        given:
        println "==== given ===="
        when:
        println "==== when ===="
        then:
        println "==== then ===="
    }

    def "should assert google once more"() {
        given:
        println "==== given ===="
        when:
        println "==== when ===="
        then:
        println "==== then ===="
    }
}
