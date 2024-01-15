package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.GroupRepository;
import com.ouiuo.timetablebot.exception.TimetableBotException;
import com.ouiuo.timetablebot.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public List<Group> findGroupByNameAndNumber(String name, int number) {
        return groupRepository.findGroupByGroupNameIgnoreCaseAndGroupNumberAndIsPracticum(name, number, false);
    }

    @Override
    public Group findGroup(String msg) throws TimetableBotException {
        String[] split = msg.split("[ -]");
        if (split.length != 2) {
            throw new TimetableBotException("Не верный разделитель, используйте или пробел или -");
        }
        List<Group> groupByNameAndNumber;
        int groupNumber;
        String groupName = split[0].toUpperCase();
        try {
            groupNumber = Integer.parseInt(split[1]);
            groupByNameAndNumber = findGroupByNameAndNumber(groupName, groupNumber);
        } catch (NumberFormatException e) {
            throw new TimetableBotException("Не верный номер группы");
        }

        if (groupByNameAndNumber.isEmpty()) {
            String replace = groupName.replace("ОZ", "OZ");
            groupByNameAndNumber = findGroupByNameAndNumber(replace, groupNumber);
            if (groupByNameAndNumber.isEmpty()) {
                throw new TimetableBotException("По указанным данным группа не найдена");
            }
        }
        if (groupByNameAndNumber.size() != 1) {
            throw new TimetableBotException("По указанным данным найдено несколько групп");
        }


        return groupByNameAndNumber.get(0);
    }
}
