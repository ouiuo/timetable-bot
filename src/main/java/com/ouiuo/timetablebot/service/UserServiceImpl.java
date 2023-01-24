package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.UsersRepository;
import com.ouiuo.timetablebot.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void updateOnline(org.telegram.telegrambots.meta.api.objects.User tUser) {
        Optional<User> byId = usersRepository.findById(tUser.getId());
        User user = byId.orElse(new User(tUser));
        user.updateLast();
        usersRepository.save(user);
    }
}
