package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.service.HistoryService;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class KeyboardCommandsProcessorBeforeDateImpl extends KeyboardCommandsProcessorAbstract {
    public KeyboardCommandsProcessorBeforeDateImpl(CasualMessageSender casualMessageSender, TimetableService timetableService, HistoryService historyService, UserService userService) {
        super(casualMessageSender, timetableService, historyService, userService);
    }

    @Override
    public int process(User user, String msg) {
        return 0;
    }

    @Override
    public KeyboardCommands getCommand() {
        return KeyboardCommands.TILL_DATE;
    }
}
