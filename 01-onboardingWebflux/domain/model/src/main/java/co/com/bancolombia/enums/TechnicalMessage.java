package co.com.bancolombia.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum TechnicalMessage {
    GENERIC_ERROR("500", "", "Tuvimos un problema, estamos tratando de arreglarlo."),
    INVALID_ID("0401", "404", "El identificador ingresado no se encuentra en la base de datos");

    private final String code;
    private final String externalCode;
    private final String message;

    public static TechnicalMessage findByExternalCode(String code) {
        return Arrays.stream(TechnicalMessage.values())
                .filter(msg -> msg.getExternalCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(GENERIC_ERROR);
    }
}
