package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.service.HistoryService;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.CANCEL;

@RequiredArgsConstructor
public abstract class KeyboardCommandsProcessorAbstract implements KeyboardCommandsProcessor {

    protected final CasualMessageSender casualMessageSender;
    protected final TimetableService timetableService;
    protected final HistoryService historyService;
    protected final UserService userService;

    @Override
    public void process(User user, String msg, KeyboardCommands keyboardCommands) {
        int result = process(user, msg);
        KeyboardCommands updateCommand = result == 0 ? CANCEL : keyboardCommands;

        afterProcess(user, msg, updateCommand);
    }

    @Override
    public void afterProcess(User user, String msg, KeyboardCommands keyboardCommands) {
        userService.updateOnline(user,keyboardCommands);
    }
}
