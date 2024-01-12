package com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationResultImpl implements ValidationResult {
    private Boolean isValid;

    private Map<Entities, Object> parsedValues = new HashMap<>();
    private final List<String> errorMessages = new ArrayList<>();

    @Override
    public boolean isValid() {
        return errorMessages.isEmpty();
    }

    @Override
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public Map<Entities, Object> getParsedMap() {
        return parsedValues;
    }
}
