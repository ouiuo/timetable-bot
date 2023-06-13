package com.ouiuo.timetablebot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "users")
public class User {
    @Id
    private Long id;

    private Date last;

    private Integer numbers;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public void updateLast() {
        numbers++;
        last = new Date();
    }

    public User(org.telegram.telegrambots.meta.api.objects.User user) {
        this.id = user.getId();
        last = new Date();
        numbers = 0;
    }

    public User() {

    }
}
