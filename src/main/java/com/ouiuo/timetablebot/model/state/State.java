package com.ouiuo.timetablebot.model.state;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.model.state.enums.States;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.KeyboardCommandsProcessor;

public interface State {

    void setUserModel(UserModel userModel);

    States getState();

    void today(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg);

    void tomorrow(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg);

    void week(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg);

    void onDate(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg);

    void cancel(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg);

    void insert(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg);
}
