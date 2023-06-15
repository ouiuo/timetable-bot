package com.ouiuo.timetablebot.dao;

import com.ouiuo.timetablebot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserModel, Long> {

}
