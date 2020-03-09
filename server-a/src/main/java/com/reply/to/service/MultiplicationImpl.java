package com.reply.to.service;


import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.reply.to.model.NumberToMultiply;
import com.reply.to.mq.RabbitMqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MultiplicationImpl implements Multiplication {

    @Autowired
    private RabbitMqSender rabbitMqSender;

    /**
     *
     * @ReadThroughSingleCache
     * Check if value exists in the cache. If it does, return it and exit cleanly.
     * If not found in the cache, then ask the underlying method for the data.
     * Before returning the data, write it out to the cache for future access.
     * Return the calculated data.
     * @param hash
     * @param numberToMultiply
     * @return
     */
    @ReadThroughSingleCache(namespace = "numbers", expiration = 3600)
    public int calculate(@ParameterValueKeyProvider int hash, NumberToMultiply numberToMultiply){
        log.trace("Calculate and cache result with key {} for numbers {}", hash, numberToMultiply);
        return rabbitMqSender.send(numberToMultiply);
    }
}
