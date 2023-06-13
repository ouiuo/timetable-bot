package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {
    void updateOnline(User user, KeyboardCommands command);
}
