package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.service.HistoryService;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class KeyboardCommandsProcessorCancelImpl extends KeyboardCommandsProcessorAbstract {

    public KeyboardCommandsProcessorCancelImpl(CasualMessageSender casualMessageSender, TimetableService timetableService, HistoryService historyService, UserService userService) {
        super(casualMessageSender, timetableService, historyService, userService);
    }

    @Override
    public KeyboardCommands getCommand() {
        return KeyboardCommands.CANCEL;
    }

    @SneakyThrows
    @Override
    public int process(User user, String msg) {
        casualMessageSender.sendTextWithButtons(user.getId(), "Выберите действие");
        return 0;
    }
}
