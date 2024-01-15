package com.ouiuo.timetablebot.model.state.enums;

import lombok.Getter;

public enum States {
    NORMIS("NORMIS"),
    ON_DATE("ON DATE"),

    NEW("NEW"),

    SELECT_GROUP("SELECT GROUP");
    @Getter
    private String name;

    States(String name) {
        this.name = name;
    }

    public static States getState(String s) {
        for (States state : values()) {
            if (state.getName().equalsIgnoreCase(s)) {
                return state;
            }
        }
        return null;
    }
}
