package com.ouiuo.timetablebot.model;

import com.ouiuo.timetablebot.model.state.NormisState;
import com.ouiuo.timetablebot.model.state.OnDateState;
import com.ouiuo.timetablebot.model.state.State;
import com.ouiuo.timetablebot.model.utils.KeyboardCommandsConverter;
import com.ouiuo.timetablebot.model.utils.StateConverter;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "users")
public class UserModel {
    @Transient
    private NormisState normisState = new NormisState(this);

    @Transient
    private OnDateState onDateState = new OnDateState(this);
    @Id
    private Long id;

    private Date last;

    private Integer numbers;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

//    @Transient
    @Convert(converter = StateConverter.class)
    private State state;

    @PostLoad
    public void init(){
        getState().setUserModel(this);
    }

    public void updateLast() {
        numbers++;
        last = new Date();
    }

    public UserModel(org.telegram.telegrambots.meta.api.objects.User user) {
        this.id = user.getId();
        last = new Date();
        numbers = 0;
    }

    public UserModel() {

    }
}
