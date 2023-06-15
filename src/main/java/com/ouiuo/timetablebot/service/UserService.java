package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {

    void updateOnline(UserModel userModel);

    UserModel loadOrCreate(User user);
}
