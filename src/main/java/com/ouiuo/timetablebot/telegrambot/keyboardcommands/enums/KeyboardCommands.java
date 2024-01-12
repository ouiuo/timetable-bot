package com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum KeyboardCommands {
    TODAY("Сегодня"),
    TOMORROW("Завтра"),
    WEEK("Неделя"),
    ON_DATE("Указать дату"),

    INSERT_GROUP("Ввести группу"),
    TILL_DATE("Указать дату До"),
    CANCEL("Отмена"),
    INSERT("Ввод данных"),

    SELECT_GROUP("/selectgroup");
    @Getter
    private final String command;

    public static KeyboardCommands getCommand(String str) {
        for (KeyboardCommands keyboardCommands : values()) {
            if (keyboardCommands.getCommand().equalsIgnoreCase(str)) {
                return keyboardCommands;
            }
        }
        return INSERT;
    }

}
