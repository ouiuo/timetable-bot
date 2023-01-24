package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.ClassesRepository;
import com.ouiuo.timetablebot.model.TrainingPair;
import org.apache.commons.lang3.time.DateUtils;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.apache.commons.lang3.time.DateUtils.*;

@Component
public class TimetableServiceImpl implements TimetableService {
    @Autowired
    ClassesRepository classesRepository;

    @Override
    public List<TrainingPair> getToday() {
        Calendar instance = GregorianCalendar.getInstance();
        instance.set(Calendar.HOUR, 0);
        Date startDate = instance.getTime();
        instance = GregorianCalendar.getInstance();
        instance.set(Calendar.HOUR, 23);
        Date endDate = instance.getTime();
        List<TrainingPair> allByStartDateGreaterThanEqualAndEndDateLessThanEqual = classesRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
        return allByStartDateGreaterThanEqualAndEndDateLessThanEqual;
    }

    @Override
    public List<TrainingPair> getTomorrow() {
        Date startDate = setHours(addDays(new Date(), 1), 0);
        Date endDate = setHours(addDays(new Date(), 1), 23);
        List<TrainingPair> allByStartDateGreaterThanEqualAndEndDateLessThanEqual = classesRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
        return allByStartDateGreaterThanEqualAndEndDateLessThanEqual;
    }

    @Override
    public TrainingPair getByDate() {
        return null;
    }

    @Override
    public List<TrainingPair> getALl() {
        return null;
    }
}
