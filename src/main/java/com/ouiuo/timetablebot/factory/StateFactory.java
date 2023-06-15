package com.ouiuo.timetablebot.factory;

import com.ouiuo.timetablebot.model.state.NormisState;
import com.ouiuo.timetablebot.model.state.OnDateState;
import com.ouiuo.timetablebot.model.state.State;
import com.ouiuo.timetablebot.model.state.enums.States;
import org.springframework.stereotype.Component;

import static com.ouiuo.timetablebot.model.state.enums.States.NORMIS;
import static com.ouiuo.timetablebot.model.state.enums.States.ON_DATE;

@Component
public class StateFactory {
    public State createState(States states) {
        if (NORMIS.equals(states)) {
            return new NormisState();
        } else if (ON_DATE.equals(states)) {
            return new OnDateState();
        }
        return new NormisState();
    }
}
