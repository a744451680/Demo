/*
package top.isyl.demo.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.lang.reflect.Method;


*/
/**
 * redis配置类 与springboot整合需配置
 *
 *//*

@Configuration
public class RedisConfig {

    */
/**
     * 序列化 key
     * @return
     *//*

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        template.setConnectionFactory(factory);

        */
/**
         * 字符串序列化
         *//*

//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        */
/**
         * jdk序列化
         *//*

        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

        // key采用String的序列化方式
//
//        template.setKeySerializer(stringRedisSerializer);
//
//        // hash的key也采用String的序列化方式
//
//        template.setHashKeySerializer(stringRedisSerializer);

        // value序列化方式采用jackson

        template.setValueSerializer(jdkSerializationRedisSerializer);

        // hash的value序列化方式采用jackson

        template.setHashValueSerializer(jdkSerializationRedisSerializer);

        template.afterPropertiesSet();

        return template;

    }

    */
/**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     *//*

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    */
/**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     *//*

    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    */
/**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     *//*

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    */
/**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     *//*

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    */
/**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     *//*

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
*/
