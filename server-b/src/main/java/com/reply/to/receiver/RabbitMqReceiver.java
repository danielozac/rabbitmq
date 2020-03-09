package com.reply.to.receiver;

import com.reply.to.model.NumberToMultiply;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RabbitMqReceiver {

	public int onMessage(NumberToMultiply numberToMultiply) {
		log.info("Executing multiplication for number {} and {} ", numberToMultiply.getNumberOne(), numberToMultiply.getNumberTwo());
		int total = numberToMultiply.getNumberOne() * numberToMultiply.getNumberTwo();
		return total;
	}
}
