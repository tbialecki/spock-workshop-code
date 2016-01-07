package pl.siili

import spock.lang.Specification

/**
 * @author Tomasz Bialecki
 */
class MockingTest extends Specification {

    class Publisher {
        List<Subscriber> subscribers = new ArrayList<>();

        void send(String message) {
            subscribers.each({
                it.receive(message)
            })
        }
    }

    interface Subscriber {
        void receive(String message)
    }


    def publisher = new Publisher();

    def "should notify subscribers"() {
        given:
            def s1 = Mock(Subscriber);
            def s2 = Mock(Subscriber);
            publisher.subscribers << s1;
            publisher.subscribers << s2;

        when:
            publisher.send("message")

        then:
            1 * s1.receive("message")
    }

    def "should allow to define number of invocations flexibly"() {
        given:
            Subscriber s1 = Mock();
            Subscriber s2 = Mock();
            publisher.subscribers << s1;
            publisher.subscribers << s2;

        when:
            publisher.send("mmm")
            publisher.send("mmm")

        then:
            (2..3) * s1.receive("mmm")
            (2..3) * s2._
    }


    def "should allow to define argument constraints"() {
        given:
            Subscriber s1 = Mock();
            Subscriber s2 = Mock();
            publisher.subscribers << s1;
            publisher.subscribers << s2;

        when:
            publisher.send("mmm")
            publisher.send("mmm")

        then:
            (2..3) * s1.receive(_ as String)
    }

    def "should allow to match any method call"() {
        given:
            Subscriber s1 = Mock();
            Subscriber s2 = Mock();
            publisher.subscribers << s1;
            publisher.subscribers << s2;

        when:
            publisher.send("mmm")
            publisher.send("mmm")

        then:
            (2..3) * s1._(_ as String)
    }
}
