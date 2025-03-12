package org.example.notificationapp.repository;

import org.example.notificationapp.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUserId(Long userId);
    void deleteByUserId(Long userId);

}
