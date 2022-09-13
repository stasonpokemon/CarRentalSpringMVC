package com.trebnikau.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public abstract class Util {

    private Util() {
    }

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));
        return errorsMap;
    }
}
