package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.UsersRepository;
import com.ouiuo.timetablebot.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;


    @Override
    public void updateOnline(UserModel userModel) {
        userModel.updateLast();
        usersRepository.save(userModel);
    }

    @Override
    public UserModel loadOrCreate(User tUser) {
        Optional<UserModel> byId = usersRepository.findById(tUser.getId());
        UserModel userModel;
        if (byId.isPresent()) {
            userModel = byId.get();
        } else {
            userModel = new UserModel(tUser);
            usersRepository.save(userModel);
        }
        return userModel;
    }
}
