package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.MessageType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class KeyboardCommandsProcessorCancelImpl extends KeyboardCommandsProcessorAbstract {

    public KeyboardCommandsProcessorCancelImpl(TimetableService timetableService, UserService userService) {
        super(timetableService, userService);
    }

    @Override
    public KeyboardCommands getCommand() {
        return KeyboardCommands.CANCEL;
    }

    @SneakyThrows
    @Override
    public void process(User user, String msg) {
        UserModel userModel = userService.loadOrCreate(user);
        userModel.getState().cancel(this, msg);
    }

    @Override
    public void process(UserModel userModel, String msg) {
        userService.updateOnline(userModel);
        messageSenderMap.get(MessageType.CASUAL).sendTextWithButtons(userModel, "Выберите действие");
    }
}
