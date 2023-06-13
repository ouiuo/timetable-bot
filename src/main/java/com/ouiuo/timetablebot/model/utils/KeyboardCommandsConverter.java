package com.ouiuo.timetablebot.model.utils;

import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class KeyboardCommandsConverter implements AttributeConverter<KeyboardCommands, String> {
    @Override
    public String convertToDatabaseColumn(KeyboardCommands keyboardCommands) {
        return keyboardCommands.getCommand();
    }

    @Override
    public KeyboardCommands convertToEntityAttribute(String s) {
        return KeyboardCommands.getCommand(s);
    }
}
