package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.HistoryRepository;
import com.ouiuo.timetablebot.model.History;
import com.ouiuo.timetablebot.model.User;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public History store(User user, KeyboardCommands keyboardCommands) {
        History history = new History(new Date(), keyboardCommands, user);
        return historyRepository.save(history);
    }

    @Override
    public History store(History history) {
        return historyRepository.save(history);
    }

    @Override
    public Optional<History> findLast(User user) {
        return historyRepository.findFirstByUserOrderByCreateDateDesc(user);
    }

    @Override
    public Optional<History> findLast(Long userId) {
        return historyRepository.findFirstByUserIdOrderByCreateDateDesc(userId);
    }
}
