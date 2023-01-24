package com.ouiuo.timetablebot.dao;

import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

}
