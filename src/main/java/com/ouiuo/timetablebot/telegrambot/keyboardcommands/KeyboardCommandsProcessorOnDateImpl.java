package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.ON_DATE;

@Service
@Slf4j
public class KeyboardCommandsProcessorOnDateImpl extends KeyboardCommandsProcessorAbstract {
    private final MessageSender chooseDateMessageSender;

    public KeyboardCommandsProcessorOnDateImpl(CasualMessageSender casualMessageSender, TimetableService timetableService, UserService userService, MessageSender chooseDateMessageSender) {
        super(casualMessageSender, timetableService, userService);
        this.chooseDateMessageSender = chooseDateMessageSender;
    }

    @Override
    public KeyboardCommands getCommand() {
        return ON_DATE;
    }

    @Override
    public void process(User user, String msg) {
        UserModel userModel = userService.loadOrCreate(user);
        userModel.getState().onDate(this, msg);
    }

    @Override
    public void process(UserModel userModel, String msg) {
        userService.updateOnline(userModel);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM", new Locale("ru"));
        DateTime now = DateTime.now(DateTimeZone.forID("Europe/Moscow"));
        chooseDateMessageSender.sendTextWithButtons(userModel.getId(), "Укажите дату в формате дд.мм например сегодня " + simpleDateFormat.format(now.toDate()));
    }
}


