package com.reply.to.configuration.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;

@Slf4j
public class CustomFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
    @Override
    public boolean isFatal(Throwable t) {
        if (t instanceof ListenerExecutionFailedException) {
            ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
            log.error("Failed to process inbound message from queue {}, failed message: {}",
                    lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
                    ,lefe.getFailedMessage(), t);
        }
        return super.isFatal(t);
    }
}
