package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.model.History;
import com.ouiuo.timetablebot.model.User;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;

import java.util.Optional;

public interface HistoryService {
    History store(User user, KeyboardCommands keyboardCommands);
    History store(History history);
    Optional<History> findLast(User user);
    Optional<History> findLast(Long userId);


}
