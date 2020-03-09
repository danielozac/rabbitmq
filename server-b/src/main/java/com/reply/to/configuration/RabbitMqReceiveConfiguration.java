package com.reply.to.configuration;

import com.reply.to.configuration.handlers.CustomFatalExceptionStrategy;
import com.reply.to.receiver.RabbitMqReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

/**
 * Direct reply-to rabbitmq config:
 * Request/Response pattern using Direct Reply-TO
 */
@Configuration
public class RabbitMqReceiveConfiguration {

    MessageListener receiver() {
        return new MessageListenerAdapter(new RabbitMqReceiver(), "onMessage");
    }

	@Bean
	public SimpleMessageListenerContainer serviceListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueues(multiply());
		container.setMessageListener(receiver());
		container.setErrorHandler(errorHandler());
		return container;
	}

	@Bean
	public ErrorHandler errorHandler() {
		return new ConditionalRejectingErrorHandler(new CustomFatalExceptionStrategy());
	}


	/**
	 * Create Queue, Exchange and binding programmatically
	 */
	@Bean
	Binding queueBinding() {
		return new Binding("multiply", Binding.DestinationType.QUEUE,
				"number_multiplication", "multiply", null);
	}

	@Bean
	public Queue multiply() {
		return new Queue("multiply");
	}

	@Bean
	Exchange numberMultiplication() {
		return new TopicExchange("number_multiplication");
	}

}
