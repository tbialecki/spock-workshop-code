package pl.siili

import spock.lang.Specification

import static org.mockito.Matchers.eq
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify
import static org.assertj.core.api.Assertions.*;
/**
 * @author Tomasz Bialecki
 */
class ExternalLibSupportTest extends Specification {
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


    def "should allow to use mockito"() {
        given:
            Subscriber s1 = mock(Subscriber);
            Subscriber s2 = mock(Subscriber);
            publisher.subscribers << s1;
            publisher.subscribers << s2;
        when:
            publisher.send("mmm")
        then:
            verify(s1).receive(eq("mmm")) || true;
    }

    def "should allow to use assertj"() {
        expect:
            assertThat("mmm").isEqualTo("mmm");
    }
}
