package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.MessageSender;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.MessageType;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
public abstract class KeyboardCommandsProcessorAbstract implements KeyboardCommandsProcessor {
    protected final TimetableService timetableService;
    protected final UserService userService;



    protected Map<MessageType, MessageSender> messageSenderMap = new HashMap<>();

    @Override
    public void unsupported(UserModel userModel, String msg) {
        messageSenderMap.get(MessageType.CASUAL).sendTextWithButtons(userModel, "Команда не поддерживается");
    }

    @Override
    public void processOnDate(UserModel userModel, Date date) {
        throw new UnsupportedOperationException("Не поддерживается");
    }

    @Override
    public void unsupportedOnDate(UserModel userModel, String msg) {
        userService.updateOnline(userModel);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM", new Locale("ru"));
        DateTime now = DateTime.now(DateTimeZone.forID("Europe/Moscow"));
        messageSenderMap.get(MessageType.CHOOSE_DATE).sendTextWithButtons(userModel, "Ошибка\n" +
                "Укажите дату в формате дд.мм например сегодня " + simpleDateFormat.format(now.toDate()));
    }

    @Override
    public void unsupportedWithCancelButton(UserModel userModel, String errorMsg) {
        userService.updateOnline(userModel);
        messageSenderMap.get(MessageType.CASUAL).sendTextWithCancelButton(userModel, errorMsg);
    }


    @Override
    public void register(MessageSender messageSender) {
        messageSenderMap.put(messageSender.getType(), messageSender);
    }
}
