package com.reply.to.configuration;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AbstractSSMConfiguration;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Distributed caching
 * Simple Spring Memcached (SSM) configuration
 */

@Configuration
public class MemCachedSSMConfiguration extends AbstractSSMConfiguration {

    @Value("${spring.memcached.host}")
    String memcachedHost;
    @Value("${spring.memcached.port}")
    int memcachedPort;

    @Bean
    public CacheFactory defaultMemcachedClient() {
        final CacheConfiguration conf = new CacheConfiguration();
        conf.setConsistentHashing(true);
        final CacheFactory cf = new CacheFactory();
        cf.setCacheClientFactory(new com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl());
        cf.setAddressProvider(new DefaultAddressProvider(String.format("%s:%d", memcachedHost, memcachedPort)));
        cf.setConfiguration(conf);
        return cf;
    }
}
