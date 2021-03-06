package com.mine.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
/*
import com.mine.queue.MessagePublisher;
import com.mine.queue.MessagePublisherImpl;
import com.mine.queue.MessageSubscriber;
*/
import com.mine.info.model.*;
@Configuration
@ComponentScan("com.model")
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String redisHost;
	@Value("${spring.redis.port}")
	private int redisPort;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setDatabase(0);
        
        JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.build());

        return jedisConFactory;
    }

    /* @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    } */ 
    
    @Bean 
    public RedisTemplate<String, Technology> redisTemplateTechnology() {
    	RedisTemplate<String, Technology> redisTemplate = new RedisTemplate<String, Technology>(); 
    	redisTemplate.setConnectionFactory(jedisConnectionFactory());
    	return redisTemplate; 
    }

    @Bean 
    public RedisTemplate<String, Info> redisTemplateInfo() {
    	RedisTemplate<String, Info> redisTemplate = new RedisTemplate<String, Info>(); 
    	redisTemplate.setConnectionFactory(jedisConnectionFactory());
    	return redisTemplate; 
    }
    
    @Bean 
    public RedisTemplate<String, Problem> redisTemplateProblem() {
    	RedisTemplate<String, Problem> redisTemplate = new RedisTemplate<String, Problem>(); 
    	redisTemplate.setConnectionFactory(jedisConnectionFactory());
    	return redisTemplate; 
    }
    
    /* @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new MessageSubscriber());
    } 
    
    
    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(), topic());
    }
    
    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    */


}