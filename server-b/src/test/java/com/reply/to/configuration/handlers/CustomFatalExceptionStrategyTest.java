package com.reply.to.configuration.handlers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomFatalExceptionStrategyTest {

    @Test
    public void isFatal_Test() {
        CustomFatalExceptionStrategy c = new CustomFatalExceptionStrategy();
        Throwable t = new Throwable();
        Boolean isFatal =  c.isFatal(t);
        assertFalse(isFatal);
    }
}
