package com.reply.to.mq;

import com.reply.to.model.NumberToMultiply;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSender {

	@Autowired
	private RabbitTemplate template;

	/**
	 * Receive a response we need to send a 'callback' queue address with the request. Spring AMQP's RabbitTemplate
	 * handles the callback queue for us when we use the convertSendAndReceive() method.
	 * For a thorough explanation please https://docs.spring.io/spring-amqp/reference/html/#request-reply
	 * @param numberToMultiply
	 * @return
	 */
	public int send(NumberToMultiply numberToMultiply) {
		Object multiplication = this.template.convertSendAndReceive("number_multiplication","multiply",
				numberToMultiply);
		return (int) multiplication;
	}
}

