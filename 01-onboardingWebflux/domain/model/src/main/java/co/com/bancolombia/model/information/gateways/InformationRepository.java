package co.com.bancolombia.model.information.gateways;

import co.com.bancolombia.model.information.Information;
import reactor.core.publisher.Mono;

public interface InformationRepository {
    Mono<Information> save(Information information);

}
