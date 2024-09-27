package co.com.bancolombia.model.information.gateways;

import co.com.bancolombia.model.information.Information;
import co.com.bancolombia.model.information.InformationDto;
import co.com.bancolombia.model.user.User;
import reactor.core.publisher.Mono;

public interface InformationSerialize {
    Information deserializeInformation(String serializedInformation);
}
