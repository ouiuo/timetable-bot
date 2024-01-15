package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.exception.TimetableBotException;
import com.ouiuo.timetablebot.model.Group;
import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.service.GroupService;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.MessageType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Date;

@Service
public class KeyboardCommandsProcessorInsertImpl extends KeyboardCommandsProcessorAbstract {

    private final GroupService groupService;

    public KeyboardCommandsProcessorInsertImpl(TimetableService timetableService, UserService userService, GroupService groupService) {
        super(timetableService, userService);
        this.groupService = groupService;
    }

    @Override
    public KeyboardCommands getCommand() {
        return KeyboardCommands.INSERT;
    }

    @SneakyThrows
    @Override
    public void process(User user, String msg) {
        UserModel userModel = userService.loadOrCreate(user);
        userModel.getState().insert(this, msg);
    }

    @Override
    public void process(UserModel userModel, String msg) {
        try {
            Group group = groupService.findGroup(msg);
            userModel.setGroup(group);
            userModel.setState(userModel.getNormisState());

        } catch (TimetableBotException e) {
            userService.updateOnline(userModel);
            unsupportedWithCancelButton(userModel, e.getMessage());
            return;
        }
        userService.updateOnline(userModel);
        messageSenderMap.get(MessageType.CASUAL).sendTextWithButtons(userModel, "Группа успешно выбрана. Выберите действие");
    }

    @Override
    public void processOnDate(UserModel userModel, Date date) {
        userService.updateOnline(userModel);
        messageSenderMap.get(MessageType.CASUAL).sendList(timetableService.getOnDate(date), userModel);
    }


}
