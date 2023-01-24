package com.ouiuo.timetablebot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "users")
public class User {
    @Id
    private Long id;

    private Date last;

    private Integer numbers;

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
