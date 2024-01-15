package com.ouiuo.timetablebot.model.state;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.model.state.enums.States;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.KeyboardCommandsProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ouiuo.timetablebot.model.state.enums.States.NEW;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewState implements State {
    private UserModel userModel;

    @Override
    public States getState() {
        return NEW;
    }

    @Override
    public void today(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void tomorrow(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void week(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void onDate(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void cancel(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void insert(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupported(userModel, msg);
    }

    @Override
    public void selectGroup(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        userModel.setState(userModel.getSelectGroupState());
        keyboardCommandsProcessor.process(userModel, msg);
    }
}