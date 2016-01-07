package pl.siili

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import pl.siili.sample.impl.SampleComponent
import spock.lang.Specification

/**
 * @author Tomasz Bialecki
 */


@ContextConfiguration(classes = SampleComponentTestConfig)
class SampleComponentTest extends Specification {

    @Autowired
    SampleComponent sampleComponent;

    def "should do it"() {

        when:
            def output = sampleComponent.doIt();

        then:
            output == "I`m doing";
    }

    @Configuration
    public static class SampleComponentTestConfig {
        @Bean
        public SampleComponent sampleComponent() {
            return new SampleComponent();
        }
    }
}
