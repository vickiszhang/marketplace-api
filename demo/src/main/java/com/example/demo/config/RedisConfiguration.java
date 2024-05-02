package com.example.demo.config;


import com.example.demo.repository.DataRepository;
import com.example.demo.repository.DataRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    @Bean
    public LettuceConnectionFactory jedisConnectionFactory() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("localhost");
        config.setPort(6379);
        System.out.println("started server");
        return new LettuceConnectionFactory(config);
    }
    @Bean
    public RedisTemplate<String, Object> RedisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());


        return template;
    }

    @Bean
    public DataRepository dataRepository() {
        return new DataRepositoryImpl();
    }


}
