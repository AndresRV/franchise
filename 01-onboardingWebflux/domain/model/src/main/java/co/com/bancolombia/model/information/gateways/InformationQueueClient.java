package co.com.bancolombia.model.information.gateways;

import co.com.bancolombia.model.information.InformationDto;
import reactor.core.publisher.Mono;

public interface InformationQueueClient {
    Mono<InformationDto> receive();
    Mono<Void> delete(String receiptHandle);
}
