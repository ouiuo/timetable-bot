package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.UsersRepository;
import com.ouiuo.timetablebot.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Override
    public void updateOnline(org.telegram.telegrambots.meta.api.objects.User tUser) {
        Optional<User> byId = usersRepository.findById(tUser.getId());
        User user = byId.orElse(new User(tUser));
        user.updateLast();
        usersRepository.save(user);
    }
}
