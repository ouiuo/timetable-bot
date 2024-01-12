package com.ouiuo.timetablebot.model.state;

import com.ouiuo.timetablebot.model.Group;
import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.model.state.enums.States;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.KeyboardCommandsProcessor;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator.ValidationResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ouiuo.timetablebot.model.state.enums.States.SELECT_GROUP;
import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator.Entities.GROUP;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectGroupState implements State {

    private UserModel userModel;

    @Override
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public States getState() {
        return SELECT_GROUP;
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
        userModel.setState(userModel.getNormisState());
        keyboardCommandsProcessor.process(userModel, msg);
    }

    @Override
    public void insert(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        ValidationResult validationResult = keyboardCommandsProcessor.validate(userModel, msg);
        if (validationResult.isValid()) {
            Group group = (Group) validationResult.getParsedMap().get(GROUP);
            userModel.setGroup(group);
            userModel.setState(userModel.getNormisState());
            keyboardCommandsProcessor.process(userModel, msg);

        } else {
            keyboardCommandsProcessor.unsupportedWithCancelButton(userModel, validationResult.getErrorMessages().get(0));

        }
    }

    @Override
    public void selectGroup(KeyboardCommandsProcessor keyboardCommandsProcessor, String msg) {
        keyboardCommandsProcessor.unsupportedWithCancelButton(userModel, "Команда не поддерживается");
    }
}
