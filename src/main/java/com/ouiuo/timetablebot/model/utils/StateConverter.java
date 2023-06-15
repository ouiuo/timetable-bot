package com.ouiuo.timetablebot.model.utils;

import com.ouiuo.timetablebot.factory.StateFactory;
import com.ouiuo.timetablebot.model.state.State;
import com.ouiuo.timetablebot.model.state.enums.States;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Converter
@Component
@RequiredArgsConstructor
public class StateConverter implements AttributeConverter<State, String> {

    private final StateFactory stateFactory;


    @Override
    public String convertToDatabaseColumn(State state) {
        return state.getState().getName();
    }

    @Override
    public State convertToEntityAttribute(String s) {
        return stateFactory.createState(States.getState(s));
    }
}
