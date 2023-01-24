package com.ouiuo.timetablebot.dao;

import com.ouiuo.timetablebot.model.TrainingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<TrainingPair, Long> {

    List<TrainingPair> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date startDate, Date endDate);
}
