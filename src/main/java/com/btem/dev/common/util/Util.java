package com.btem.dev.common.util;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static Map getValidResult(Errors errors) {
        Map<String, String> validResult = new HashMap<>();
        for(FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validResult.put(validKeyName, error.getDefaultMessage());
        }
        return validResult;
    }
}
