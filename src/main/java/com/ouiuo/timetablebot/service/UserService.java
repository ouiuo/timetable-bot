package com.ouiuo.timetablebot.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {
    void updateOnline(User user);
}
