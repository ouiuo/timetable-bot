package com.ouiuo.timetablebot.dao;

import com.ouiuo.timetablebot.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findGroupByGroupNameAndGroupNumberAndIsPracticum(String groupName, Long groupNumber, Boolean isPracticum);

    List<Group> findGroupByGroupNameIgnoreCaseAndGroupNumberAndIsPracticum(String groupName, int groupNumber, Boolean isPracticum);

}
