package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.exception.TimetableBotException;
import com.ouiuo.timetablebot.model.Group;

import java.util.List;

public interface GroupService {
    List<Group> findGroupByNameAndNumber(String name, int number);

    Group findGroup(String msg) throws TimetableBotException;
}
