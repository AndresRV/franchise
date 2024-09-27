package co.com.bancolombia.usecase.information;

import co.com.bancolombia.model.information.Information;
import co.com.bancolombia.model.information.gateways.InformationQueueClient;
import co.com.bancolombia.model.information.gateways.InformationRepository;
import co.com.bancolombia.model.information.gateways.InformationSerialize;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class InformationUseCase {
    private final InformationQueueClient informationQueueClient;
    private final InformationSerialize informationSerialize;
    private final InformationRepository informationRepository;

    public Mono<Information> saveInformationUser() {
        return informationQueueClient.receive()
                .flatMap(informationDto -> {
                    Information information = deserializeInformation(informationDto.getInformationUser()).toUpperCase();
                    return informationRepository.save(information)
                            .then(informationQueueClient.delete(informationDto.getReceiptHandle())
                            .thenReturn(information));
                });
    }

    private Information deserializeInformation(String serializedInformation) {
        return informationSerialize.deserializeInformation(serializedInformation);
    }
}
