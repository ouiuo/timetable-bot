package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.telegrambot.TelegramBot;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Date;

public interface KeyboardCommandsProcessor {
    KeyboardCommands getCommand();

    @Autowired
    default void register(TelegramBot telegramBot) {
        telegramBot.registrateCommandProcessor(getCommand(), this);
    }

    void register(MessageSender messageSender);


    void process(User user, String msg);

    void unsupported(UserModel userModel, String msg);

    void unsupportedOnDate(UserModel userModel, String msg);

    void process(UserModel userModel, String msg);

    void processOnDate(UserModel userModel, Date date);

    void unsupportedWithCancelButton(UserModel userModel, String msg);

}
