package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.model.Group;
import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.model.UserModel;

import java.util.Date;
import java.util.List;

public interface TimetableService {
    List<TrainingPair> getToday(UserModel userModel);

    List<TrainingPair> getTomorrow(UserModel userModel);

    List<TrainingPair> getWeek(UserModel userModel);

    TrainingPair getByDate();

    List<TrainingPair> getALl();

    List<TrainingPair> getOnDate(Date parse, UserModel userModel);

    boolean isExistByGroup(Group group);
}
