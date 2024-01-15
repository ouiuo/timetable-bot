package com.ouiuo.timetablebot.model.state;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.model.state.enums.States;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.KeyboardCommandsProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.ouiuo.timetablebot.model.state.enums.States.ON_DATE;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OnDateState implements State {

    private UserModel userModel;

    @Override
    public States getState() {
        return ON_DATE;
    }

    @Override
    public void today(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupportedOnDate(userModel, msg);
    }


    @Override
    public void tomorrow(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupportedOnDate(userModel, msg);
    }

    @Override
    public void week(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupportedOnDate(userModel, msg);
    }

    @Override
    public void onDate(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupportedOnDate(userModel, msg);
    }

    @Override
    public void cancel(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        userModel.setState(userModel.getNormisState());
        keyboardCommandsProcessor.process(userModel, msg);
    }

    @Override
    public void insert(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM", new Locale("ru"));
        try {
            Date parse = simpleDateFormat.parse(msg);
            userModel.setState(userModel.getNormisState());
            keyboardCommandsProcessor.processOnDate(userModel, parse);
        } catch (ParseException e) {
            keyboardCommandsProcessor.unsupportedOnDate(userModel, msg);
        }
    }

    @Override
    public void selectGroup(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupportedOnDate(userModel, msg);
    }
}
