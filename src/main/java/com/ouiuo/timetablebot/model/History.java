package com.ouiuo.timetablebot.model;

import com.ouiuo.timetablebot.model.utils.KeyboardCommandsConverter;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createDate;

    @Convert(converter = KeyboardCommandsConverter.class)
    private KeyboardCommands keyboardCommands;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public History(Date createDate, KeyboardCommands keyboardCommands, User user) {
        this.createDate = createDate;
        this.keyboardCommands = keyboardCommands;
        this.user = user;
    }

    ;

}
