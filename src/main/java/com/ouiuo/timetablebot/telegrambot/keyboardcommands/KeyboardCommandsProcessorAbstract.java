package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public abstract class KeyboardCommandsProcessorAbstract implements KeyboardCommandsProcessor {

    protected final CasualMessageSender casualMessageSender;
    protected final TimetableService timetableService;
    protected final UserService userService;

    @Override
    public void unsupported(UserModel userModel, String msg) {
        casualMessageSender.sendTextWithButtons(userModel.getId(), "Команда не поддерживается");
    }

    @Override
    public void processOnDate(UserModel userModel, Date date) {
        throw new UnsupportedOperationException("Не поддерживается");
    }

    @Override
    public void unsupportedOnDate(UserModel userModel, String msg) {
        throw new UnsupportedOperationException("Не поддерживается");
    }
}
