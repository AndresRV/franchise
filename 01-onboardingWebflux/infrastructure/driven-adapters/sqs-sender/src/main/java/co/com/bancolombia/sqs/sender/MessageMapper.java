package co.com.bancolombia.sqs.sender;

import co.com.bancolombia.model.information.InformationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import software.amazon.awssdk.services.sqs.model.Message;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    @Mapping(target = "informationUser", expression = "java(message.body())")
    @Mapping(target = "receiptHandle", expression = "java(message.receiptHandle())")
    InformationDto toInformationDto(Message message);
}