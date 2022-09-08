package com.trebnikau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UtilsController {

    static Map<String, String> getErrors(BindingResult bindingResult){
        Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));
        return errorsMap;
    }
}
