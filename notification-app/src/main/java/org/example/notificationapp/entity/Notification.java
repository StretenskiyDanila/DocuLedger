package org.example.notificationapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.notificationapp.entity.enums.NotificationChannel;
import org.example.notificationapp.entity.enums.StateEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String userName;

    private String userMail;

    private String tabName;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private StateEnum state;

    @Column(name = "channel")
    @Enumerated(value = EnumType.STRING)
    private NotificationChannel channel;

    @Column(name = "enable")
    private Boolean enable;

}
