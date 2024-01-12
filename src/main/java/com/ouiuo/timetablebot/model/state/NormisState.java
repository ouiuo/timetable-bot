package com.ouiuo.timetablebot.model.state;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.model.state.enums.States;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.KeyboardCommandsProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ouiuo.timetablebot.model.state.enums.States.NORMIS;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NormisState implements State {

    @Override
    public States getState() {
        return NORMIS;
    }

    private UserModel userModel;

    @Override
    public void today(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.process(userModel, msg);
    }

    @Override
    public void tomorrow(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.process(userModel, msg);
    }

    @Override
    public void week(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.process(userModel, msg);
    }

    @Override
    public void onDate(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        userModel.setState(userModel.getOnDateState());
        keyboardCommandsProcessor.process(userModel, msg);
    }

    @Override
    public void insert(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void cancel(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void selectGroup(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        userModel.setState(userModel.getSelectGroupState());
        keyboardCommandsProcessor.process(userModel, msg);
    }
}
