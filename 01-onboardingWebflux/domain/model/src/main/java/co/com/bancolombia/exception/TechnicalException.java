package co.com.bancolombia.exception;

import co.com.bancolombia.enums.TechnicalMessage;
import lombok.Getter;

@Getter
public class TechnicalException extends OnboardingWebfluxException {
    public TechnicalException(TechnicalMessage technicalMessage) {
        super(technicalMessage);
    }

    public TechnicalException(Throwable cause, TechnicalMessage technicalMessage) {
        super(cause, technicalMessage);
    }
}
