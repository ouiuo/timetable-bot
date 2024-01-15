package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.ClassesRepository;
import com.ouiuo.timetablebot.model.Group;
import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.model.UserModel;
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
    public List<TrainingPair> getToday(UserModel userModel) {
        Date startDate = getTodayDateTime().withHourOfDay(0).toDate();
        Date endDate = getTodayDateTime().withHourOfDay(23).toDate();
        return classesRepository.findAllByGroupIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(userModel.getGroup().getId(), startDate, endDate);
    }

    @Override
    public List<TrainingPair> getTomorrow(UserModel userModel) {
        Date startDate = getTomorrowDateTime().withHourOfDay(0).toDate();
        Date endDate = getTomorrowDateTime().withHourOfDay(23).toDate();
        return classesRepository.findAllByGroupIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(userModel.getGroup().getId(), startDate, endDate);
    }

    @Override
    public List<TrainingPair> getWeek(UserModel userModel) {
        Date startDate = getTodayDateTime().withHourOfDay(0).toDate();
        Date endDate = getWeekDateTime().withHourOfDay(23).toDate();
        return classesRepository.findAllByGroupIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(userModel.getGroup().getId(), startDate, endDate);
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
    public List<TrainingPair> getOnDate(Date parse, UserModel userModel) {
        DateTime todayDateTime = getTodayDateTime();
        DateTime dateTime = new DateTime(parse);
        dateTime = dateTime.withYear(todayDateTime.getYear());
        Date startDate = dateTime.withHourOfDay(0).toDate();
        Date endDate = dateTime.withHourOfDay(23).toDate();
        return classesRepository.findAllByGroupIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(userModel.getGroup().getId(), startDate, endDate);
    }

    @Override
    public boolean isExistByGroup(Group group) {
        return classesRepository.existsByGroupId(group.getId());
    }
}
