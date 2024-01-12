package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class KeyboardCommandsProcessorSelectGroupImpl extends KeyboardCommandsProcessorAbstract {

    public KeyboardCommandsProcessorSelectGroupImpl(CasualMessageSender casualMessageSender, TimetableService timetableService, UserService userService) {
        super(casualMessageSender, timetableService, userService);
    }

    @Override
    public KeyboardCommands getCommand() {
        return KeyboardCommands.SELECT_GROUP;
    }

    @Override
    public void process(User user, String msg) {
        UserModel userModel = userService.loadOrCreate(user);
        userModel.getState().selectGroup(this, msg);
    }

    @Override
    public void process(UserModel userModel, String msg) {
        userService.updateOnline(userModel);
        casualMessageSender.sendTextWithCancelButton(userModel.getId(), "Введите имя группы и номер через пробел, например ПМИOZ 311 или ПМИOZ-311");
    }
}
