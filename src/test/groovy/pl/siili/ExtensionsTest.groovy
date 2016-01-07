package pl.siili

import spock.lang.IgnoreIf
import spock.lang.IgnoreRest
import spock.lang.Specification
import spock.lang.Title

/**
 * @author Tomasz Bialecki
 */
@Title("This is easy to read")
class ExtensionsTest extends Specification {


    def "will be ignored due to @IgnoreRest"() {

    }

    @IgnoreRest
    def "will ignore rest"() {
        expect:
            true;
    }

    @IgnoreIf({ System.getProperty("os.name").contains("Mac") })
    def "spec 2"() {
        expect:
            true;
    }
}
