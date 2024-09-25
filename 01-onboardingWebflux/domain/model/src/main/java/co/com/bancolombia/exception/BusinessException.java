package co.com.bancolombia.exception;

import co.com.bancolombia.enums.TechnicalMessage;
import lombok.Getter;

@Getter
public class BusinessException extends OnboardingWebfluxException {
    public BusinessException(TechnicalMessage technicalMessage) {
        super(technicalMessage);
    }

    public BusinessException(String message, TechnicalMessage technicalMessage) {
        super(message, technicalMessage);
    }
}
