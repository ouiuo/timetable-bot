package com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums;

import lombok.Getter;

import java.util.List;

public enum KeyboardCommands {
    TODAY("Сегодня"),
    TOMORROW("Завтра"),
    WEEK("Неделя"),
    ON_DATE("Указать дату"),
    TILL_DATE("Указать дату До"),
    CANCEL("Отмена"),
    INSERT("Ввод данных"),

    SELECT_GROUP("Ввести группу", "/selectgroup");
    @Getter
    private final List<String> commands;

    KeyboardCommands(String... command) {
        this.commands = List.of(command);
    }

    public String getCommand() {
        return commands.get(0);
    }

    public static KeyboardCommands getCommand(String str) {
        for (KeyboardCommands keyboardCommands : values()) {
            if (keyboardCommands.getCommands().contains(str)) {
                return keyboardCommands;
            }
        }
        return INSERT;
    }

}
