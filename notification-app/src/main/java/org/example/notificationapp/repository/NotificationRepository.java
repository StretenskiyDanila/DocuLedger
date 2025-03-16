package org.example.notificationapp.repository;

import org.example.notificationapp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUserId(Long userId);
    void deleteByIdAndTabName(Long id, String tabName);
    List<Notification> findAllByState(Notification.StateEnum state);
    //List<Notification> findAllByStateAndUserMail(Notification.StateEnum state, String userMail);

}
