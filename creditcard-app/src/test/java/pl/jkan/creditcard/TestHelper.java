package pl.jkan.creditcard;

import junit.framework.TestCase;

public class TestHelper extends TestCase {
    public static <X extends Throwable> Throwable assertThrows(final Class<X> exceptionClass, final Runnable block) {
        try {
            block.run();
        } catch(Throwable ex) {
            if(exceptionClass.isInstance(ex))
                return ex;
        }
        fail("Failed to throw expected exception ");
        return null;
    }
}