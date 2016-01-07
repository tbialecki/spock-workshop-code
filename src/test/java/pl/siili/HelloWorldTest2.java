package pl.siili;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Tomasz Bialecki
 */
public class HelloWorldTest2 {
    @Test
    public void plusPlusIShouldIterate() {
        //given
        int i = 0;
        //when
        i = ++i;
        //then
        assertEquals(i, 1);
    }
}
