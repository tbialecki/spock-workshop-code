package pl.siili

import groovy.transform.ToString
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Tomasz Bialecki
 */
class DataDrivenSpockTest extends Specification {


    @ToString
    class DummySumTo {
        int a;
        int b;

        DummySumTo(int a, int b) {
            this.a = a
            this.b = b
        }

        def sum() {
            return a + b;
        }
    }


    def "Math.max should determine max value"(int a, int b, int max) {

        expect:
            Math.max(a, b) == max

        where:
            a | b || max
            1 | 2 || 2
            1 | 1 || 1
            3 | 2 || 3
    }

    def "Math.max should determine max value - pipes"(int a, int b, int max) {

        expect:
            Math.max(a, b) == max

        where:
            a << [1, 1, 3]
            b << [2, 1, 2]
            max << [2, 1, 3]
    }

    @Unroll
    def "spock should allow to define meaningful method names -> Max(#a,#b)===#max"() {
        expect:
            Math.max(a, b) == max

        where:
            a | b || max
            1 | 2 || 2
            1 | 1 || 1
            3 | 2 || 3
    }

    @Unroll
    def "data driven tests should be also possible for more complex objects (#bean -> sum=#sum)"() {
        expect:
            bean.sum() == sum

        where:
            bean                 || sum
            new DummySumTo(1, 2) || 3
            new DummySumTo(1, 1) || 2
    }
}
