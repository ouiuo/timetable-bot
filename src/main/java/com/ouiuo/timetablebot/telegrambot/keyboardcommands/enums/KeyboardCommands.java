package com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum KeyboardCommands {
    TODAY("Сегодня", false, false),
    TOMORROW("Завтра", false, false),
    WEEK("Неделя", false, false),
    ON_DATE("Указать дату", false, true),
    TILL_DATE("Указать дату До", false, true),
    CANCEL("Отмена", true, false),
    UNSUPPORTED("Операция не поддерживается", false, false);
    @Getter
    private final String command;
    @Getter
    private final Boolean isAbort;
    @Getter
    private final Boolean isBranching;

    public static KeyboardCommands getCommand(String str) {
        for (KeyboardCommands keyboardCommands : values()) {
            if (keyboardCommands.getCommand().equalsIgnoreCase(str)) {
                return keyboardCommands;
            }
        }
        return UNSUPPORTED;
    }

    public static KeyboardCommands getPriorityCommand(KeyboardCommands current, KeyboardCommands previous) {
        if (previous.getIsBranching()) {
            return current.getIsAbort() ? current : previous;
        } else {
            return current;
        }
    }
}
