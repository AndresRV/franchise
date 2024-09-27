package co.com.bancolombia.serializeutil;

import co.com.bancolombia.model.information.Information;
import co.com.bancolombia.model.information.gateways.InformationSerialize;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserSerialize;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SerializeInformationUtil implements InformationSerialize {
    private final ObjectMapper objectMapper;

    public SerializeInformationUtil() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Information deserializeInformation(String serializedInformation) {
        try {
            return objectMapper.readValue(serializedInformation, Information.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
