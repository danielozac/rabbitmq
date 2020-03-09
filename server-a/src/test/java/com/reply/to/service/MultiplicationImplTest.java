package com.reply.to.service;

import com.reply.to.model.NumberToMultiply;
import com.reply.to.mq.RabbitMqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MultiplicationImplTest {

    @InjectMocks
    private MultiplicationImpl multiplication;

    @Mock
    private RabbitMqSender rabbitMqSender;;

    @Test
    public void calculate_test() {
        NumberToMultiply numbersToMultiply = new NumberToMultiply(1,3);
        when(rabbitMqSender.send(numbersToMultiply)).thenReturn(4);
        int total  = multiplication.calculate(123, numbersToMultiply);
        assertEquals(4, total);
    }

}
