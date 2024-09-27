package co.com.bancolombia.sqs.sender;

import co.com.bancolombia.model.information.InformationDto;
import co.com.bancolombia.model.information.gateways.InformationQueueClient;
import co.com.bancolombia.sqs.sender.config.SQSSenderProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@Service
@Log4j2
@RequiredArgsConstructor
public class SQSReceiver implements InformationQueueClient {
    private final SQSSenderProperties properties;
    private final SqsAsyncClient client;
    private final MessageMapper messageMapper;

    private static final Integer MAX_RECEIVE_MESSAGES = 1; //puede ir hasta 10
    private static final Integer LONG_POLLING = 20; //mantiene la conexion abierta por segundos esperando un mensaje

    @Override
    public Mono<InformationDto> receive() {
        return Mono.fromFuture(() -> client.receiveMessage(buildReceiveRequest()))
                .flatMapMany(response -> Flux.fromIterable(response.messages()))
                .next()
                .map(messageMapper::toInformationDto);
    }

    @Override
    public Mono<Void> delete(String receiptHandle) {
        return Mono.fromFuture(() -> client.deleteMessage(buildDeleteRequest(receiptHandle)))
                .then();
    }

    private ReceiveMessageRequest buildReceiveRequest() {
        return ReceiveMessageRequest.builder()
                .queueUrl(properties.getQueueUrl())
                .maxNumberOfMessages(MAX_RECEIVE_MESSAGES)
                .waitTimeSeconds(LONG_POLLING)
                .build();
    }

    private DeleteMessageRequest buildDeleteRequest(String receiptHandle) {
        return DeleteMessageRequest.builder()
                .queueUrl(properties.getQueueUrl())
                .receiptHandle(receiptHandle)
                .build();
    }
}
