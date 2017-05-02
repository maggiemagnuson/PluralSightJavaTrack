package com.maggie.fundamentals;

import java.io.IOException;

/**
 * Created by z001hqv on 2/8/17.
 */
public class AutoCloseableImpl implements AutoCloseable {

    public void saySomething() throws IOException{
        throw new IOException("Intentional exception thrown from saySomething()");
        //System.out.println("Something");
    }

    @Override
    public void close() throws IOException {
        throw new IOException("Intentional exception thrown from close()");
        //System.out.println("AutoCloseable.close()");
    }
}
