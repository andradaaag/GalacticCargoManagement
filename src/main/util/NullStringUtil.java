package main.util;

import main.domain.GCMException;

import java.util.Optional;

public class NullStringUtil {
    public static Optional<String> returnEmptyOptionalIfNullString(String input) {
        return Optional.ofNullable(input).filter(s -> !s.isEmpty());
    }

    public static String checkIfNullInput(String input, String errorMessage) {
        Optional<String> stringOptional = returnEmptyOptionalIfNullString(input);
        return stringOptional.orElseThrow(() -> new GCMException(errorMessage));
    }
}
