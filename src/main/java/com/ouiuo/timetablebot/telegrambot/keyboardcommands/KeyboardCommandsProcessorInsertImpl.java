package com.ouiuo.timetablebot.telegrambot.keyboardcommands;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.model.state.enums.States;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.CasualMessageSender;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler.ChooseDateMessageSender;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator.ValidationResult;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator.Validator;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator.ValidatorAware;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class KeyboardCommandsProcessorInsertImpl extends KeyboardCommandsProcessorAbstract implements ValidatorAware {

    protected final ChooseDateMessageSender chooseDateMessageSender;

    protected final Map<States, Validator> validatorMap = new HashMap<>();

    public KeyboardCommandsProcessorInsertImpl(CasualMessageSender casualMessageSender, TimetableService timetableService, UserService userService, ChooseDateMessageSender chooseDateMessageSender) {
        super(casualMessageSender, timetableService, userService);
        this.chooseDateMessageSender = chooseDateMessageSender;
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
        userService.updateOnline(userModel);
        casualMessageSender.sendTextWithButtons(userModel.getId(), "Выберите действие");
    }

    @Override
    public void processOnDate(UserModel userModel, Date date) {
        userService.updateOnline(userModel);
        casualMessageSender.sendList(timetableService.getOnDate(date), userModel);
    }

    @Override
    public void unsupportedOnDate(UserModel userModel, String msg) {
        userService.updateOnline(userModel);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM", new Locale("ru"));
        DateTime now = DateTime.now(DateTimeZone.forID("Europe/Moscow"));
        chooseDateMessageSender.sendTextWithButtons(userModel.getId(), "Ошибка\n" +
                "Укажите дату в формате дд.мм например сегодня " + simpleDateFormat.format(now.toDate()));
    }

    @Override
    public void registrateValidator(States states, Validator validator) {
        validatorMap.put(states, validator);
    }

    @Override
    public ValidationResult validate(UserModel userModel, String msg) {
        return validatorMap.get(userModel.getState().getState()).validate(msg);
    }
}
