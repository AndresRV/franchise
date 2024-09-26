package co.com.bancolombia.serializeutil;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserSerialize;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SerializeUtil implements UserSerialize {
    private final ObjectMapper objectMapper;

    public SerializeUtil() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String serializeUSer(User user) {
        try {
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
