package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.service.HistoryService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.ON_DATE;

@Service
@Slf4j
public class KeyboardCommandsProcessorOnDateImpl extends KeyboardCommandsProcessorAbstract {
    private final MessageSender chooseDateMessageSender;

    public KeyboardCommandsProcessorOnDateImpl(CasualMessageSender casualMessageSender, TimetableService timetableService, HistoryService historyService, UserService userService, MessageSender chooseDateMessageSender) {
        super(casualMessageSender, timetableService, historyService, userService);
        this.chooseDateMessageSender = chooseDateMessageSender;
    }

    @Override
    public KeyboardCommands getCommand() {
        return ON_DATE;
    }

    @Override
    public int process(User user, String msg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM", new Locale("ru"));
        DateTime now = DateTime.now(DateTimeZone.forID("Europe/Moscow"));
        if (msg.equals(ON_DATE.getCommand())) {
            chooseDateMessageSender.sendTextWithButtons(user.getId(), "Укажите дату в формате дд.мм например сегодня " + simpleDateFormat.format(now.toDate()));
            return 1;
        } else {
            try {
                Date parse = simpleDateFormat.parse(msg);
                casualMessageSender.sendList(timetableService.getOnDate(parse), user);
                return 0;
            } catch (ParseException e) {
                chooseDateMessageSender.sendTextWithButtons(user.getId(), "Не верный формат даты, " + simpleDateFormat.format(now.toDate()));
                return 1;
            }
        }
    }

}
