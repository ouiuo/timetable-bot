package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.service.HistoryService;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class KeyboardCommandsProcessorWeekImpl extends KeyboardCommandsProcessorAbstract {

    public KeyboardCommandsProcessorWeekImpl(CasualMessageSender casualMessageSender, TimetableService timetableService, HistoryService historyService, UserService userService) {
        super(casualMessageSender, timetableService, historyService, userService);
    }

    @Override
    public KeyboardCommands getCommand() {
        return KeyboardCommands.WEEK;
    }

    @Override
    public int process(User user, String msg) {
        casualMessageSender.sendList(timetableService.getWeek(), user);
        return 0;
    }

}
