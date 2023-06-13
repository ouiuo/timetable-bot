package com.ouiuo.timetablebot.dao;

import com.ouiuo.timetablebot.model.History;
import com.ouiuo.timetablebot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findFirstByUserOrderByCreateDateDesc(User user);

    Optional<History> findFirstByUserIdOrderByCreateDateDesc(Long userId);

}
