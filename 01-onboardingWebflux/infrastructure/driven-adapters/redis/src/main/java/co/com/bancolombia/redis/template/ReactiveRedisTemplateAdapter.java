package co.com.bancolombia.redis.template;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserCache;
import co.com.bancolombia.redis.template.helper.ReactiveTemplateAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class ReactiveRedisTemplateAdapter extends ReactiveTemplateAdapterOperations<User,
        String, UserRedisModel>
 implements UserCache
{
    public ReactiveRedisTemplateAdapter(ReactiveRedisConnectionFactory connectionFactory, ObjectMapper mapper) {
        super(connectionFactory, mapper, d -> mapper.map(d, User.class));
    }
}
