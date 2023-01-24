package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.model.TrainingPair;

import java.util.List;

public interface TimetableService {
    List<TrainingPair> getToday();

    List<TrainingPair> getTomorrow();

    TrainingPair getByDate();

    List<TrainingPair> getALl();
}
