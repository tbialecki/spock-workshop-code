package pl.siili

import spock.lang.Specification

/**
 * @author Tomasz Bialecki
 */
class StubbingTest extends Specification {

    class Publisher {
        List<Subscriber> subscribers = new ArrayList<>();

        void send(String message) {
            subscribers.each({
                it.receive(message)
            })
        }
    }

    interface Subscriber {
        String receive(String message)
    }


    def publisher = new Publisher();

    def "should notify subscribers"() {
        given:
            def s1 = Stub(Subscriber) {
                receive(_) >> "ok"
            };
            def s2 = Stub(Subscriber) {
                receive(_) >> { throw new NullPointerException() };
            };
            publisher.subscribers << s1;
            publisher.subscribers << s2;

        when:
            publisher.send("message")

        then:
            s1.receive("message")
    }
}
