package co.com.bancolombia.exception;

import co.com.bancolombia.enums.TechnicalMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OnboardingWebfluxException extends RuntimeException {
    private final TechnicalMessage technicalMessage;

    public OnboardingWebfluxException(String message, TechnicalMessage technicalMessage) {
        super(message);
        this.technicalMessage = technicalMessage;
    }

    public OnboardingWebfluxException(Throwable cause, TechnicalMessage technicalMessage) {
        super(cause);
        this.technicalMessage = technicalMessage;
    }
}
