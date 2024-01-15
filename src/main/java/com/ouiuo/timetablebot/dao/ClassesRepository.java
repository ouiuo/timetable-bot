package com.ouiuo.timetablebot.dao;

import com.ouiuo.timetablebot.model.TrainingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClassesRepository extends JpaRepository<TrainingPair, Long> {

    List<TrainingPair> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date startDate, Date endDate);

    List<TrainingPair> findAllByGroupIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(UUID groupId, Date startDate, Date endDate);


    boolean existsByGroupId(UUID groupId);
}
