package com.ouiuo.timetablebot.telegrambot.keyboardcommands.validator;

import com.ouiuo.timetablebot.model.state.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Validator {
    ValidationResult validate(String msg);

    States getState();

    @Autowired
    default void registrate(List<ValidatorAware> validatorAwareList) {
        validatorAwareList.forEach(p  -> p.registrateValidator(getState(), this));
    };
}
