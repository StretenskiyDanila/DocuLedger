package org.example.notificationapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public enum StateEnum {
        RUNNING,
        FINISHED
    }

}
