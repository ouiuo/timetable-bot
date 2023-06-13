package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.ClassesRepository;
import com.ouiuo.timetablebot.model.TrainingPair;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TimetableServiceImpl implements TimetableService {
    @Autowired
    ClassesRepository classesRepository;

    @Override
    public List<TrainingPair> getToday() {
        Date startDate = getTodayDateTime().withHourOfDay(0).toDate();
        Date endDate = getTodayDateTime().withHourOfDay(23).toDate();
        return classesRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
    }

    @Override
    public List<TrainingPair> getTomorrow() {
        Date startDate = getTomorrowDateTime().withHourOfDay(0).toDate();
        Date endDate = getTomorrowDateTime().withHourOfDay(23).toDate();
        return classesRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
    }

    @Override
    public List<TrainingPair> getWeek() {
        Date startDate = getTodayDateTime().withHourOfDay(0).toDate();
        Date endDate = getWeekDateTime().withHourOfDay(23).toDate();
        return classesRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
    }


    private DateTime getTodayDateTime() {
        return DateTime.now(DateTimeZone.forID("Europe/Moscow"));
    }

    private DateTime getTomorrowDateTime() {
        return getTodayDateTime().plusDays(1);
    }

    private DateTime getWeekDateTime() {
        return getTodayDateTime().plusDays(7);
    }

    @Override
    public TrainingPair getByDate() {
        return null;
    }

    @Override
    public List<TrainingPair> getALl() {
        return null;
    }

    @Override
    public List<TrainingPair> getOnDate(Date parse) {
        DateTime todayDateTime = getTodayDateTime();
        DateTime dateTime = new DateTime(parse);
        dateTime = dateTime.withYear(todayDateTime.getYear());
        Date startDate = dateTime.withHourOfDay(0).toDate();
        Date endDate = dateTime.withHourOfDay(23).toDate();
        return classesRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
    }
}
