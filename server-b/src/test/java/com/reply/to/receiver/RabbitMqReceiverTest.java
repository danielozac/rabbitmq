package com.reply.to.receiver;


import com.reply.to.model.NumberToMultiply;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class RabbitMqReceiverTest {

    NumberToMultiply numbersToMultiply = null;

    @Before
    public void init() {
        numbersToMultiply = new NumberToMultiply();
        numbersToMultiply.setNumberOne(2);
        numbersToMultiply.setNumberTwo(3);
    }

    @Test
    public void onMessage_Test() {
        RabbitMqReceiver rabbitMqReceiver = new RabbitMqReceiver();
        int total  = rabbitMqReceiver.onMessage(numbersToMultiply);
        assertEquals(total,6);
    }
}
