package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.telegrambot.TelegramBot;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.User;

public interface KeyboardCommandsProcessor {
    KeyboardCommands getCommand();

    @Autowired
    default void register(TelegramBot telegramBot) {
        telegramBot.registrateCommandProcessor(getCommand(), this);
    }

    void process(User user, String msg, KeyboardCommands keyboardCommands);

    int process(User user, String msg);

    void afterProcess(User user, String msg, KeyboardCommands keyboardCommands);


}
