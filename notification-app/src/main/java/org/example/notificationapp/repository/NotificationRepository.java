package org.example.notificationapp.repository;

import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.entity.enums.NotificationChannel;
import org.example.notificationapp.entity.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    boolean existsByUserName(String userName);
    List<Notification> findAllByState(StateEnum state);
    Optional<Notification> findByUserNameAndTabNameAndChannel(String userName, String tabName, NotificationChannel channel);
    Optional<Notification> findByUserMailAndTabNameAndChannel(String userMail, String tabName, NotificationChannel channel);
    Optional<Notification> getFirstByUserName(String userName);
    List<Notification> findAllByUserNameAndChannel(String userName, NotificationChannel channel);
    List<Notification> findAllByUserMailAndChannel(String userMail, NotificationChannel channel);
    List<Notification> findAllByUserName(String userName);
    List<Notification> findAllByUserMail(String userMail);

}
