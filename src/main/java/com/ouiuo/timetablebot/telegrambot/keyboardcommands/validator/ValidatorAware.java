package com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator;


import com.ouiuo.timetablebot.model.state.enums.States;

public interface ValidatorAware {
    void registrateValidator(States states, Validator validator);
}
