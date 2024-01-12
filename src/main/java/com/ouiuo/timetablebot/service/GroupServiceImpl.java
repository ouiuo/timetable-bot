package com.ouiuo.timetablebot.service;

import com.ouiuo.timetablebot.dao.GroupRepository;
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
}
