package org.example.notificationapp.repository;

import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.entity.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    boolean existsByUserName(String userName);
    List<Notification> findAllByState(StateEnum state);

}
