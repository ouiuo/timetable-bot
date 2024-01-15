package com.ouiuo.timetablebot.model;

import com.ouiuo.timetablebot.model.state.*;
import com.ouiuo.timetablebot.model.utils.StateConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "users")
public class UserModel {
    @Transient
    private State normisState = new NormisState(this);

    @Transient
    private State onDateState = new OnDateState(this);
    @Transient
    private State newState = new NewState(this);

    @Transient
    private SelectGroupState selectGroupState = new SelectGroupState(this);
    @Id
    private Long id;

    private Date last;

    private Integer numbers;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Convert(converter = StateConverter.class)
    private State state;

    @PostLoad
    public void init() {
        getState().setUserModel(this);
    }

    public void updateLast() {
        numbers++;
        last = new Date();
    }

    public UserModel(org.telegram.telegrambots.meta.api.objects.User user) {
        this.id = user.getId();
        last = new Date();
        state = newState;
        numbers = 0;
    }

    public UserModel() {

    }
}
