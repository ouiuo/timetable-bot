package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.model.TrainingPair;

import java.util.Date;
import java.util.List;

public interface TimetableService {
    List<TrainingPair> getToday();

    List<TrainingPair> getTomorrow();

    List<TrainingPair> getWeek();

    TrainingPair getByDate();

    List<TrainingPair> getALl();

    List<TrainingPair> getOnDate(Date parse);
}
