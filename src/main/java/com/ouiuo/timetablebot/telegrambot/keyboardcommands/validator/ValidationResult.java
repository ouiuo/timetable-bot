package com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator;

import java.util.List;
import java.util.Map;

public interface ValidationResult {

    Map<Entities, Object> getParsedMap();
    boolean isValid();

    List<String> getErrorMessages();
}
