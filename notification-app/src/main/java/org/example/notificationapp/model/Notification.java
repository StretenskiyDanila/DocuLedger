package org.example.notificationapp.model;

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
    private Long userId;

    private String userName;

    private String tabName;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private StateEnum state;

    //TODO: добавить обработку ошибки и подробный вывод связанный с состояниями
    public enum StateEnum {
        RUNNING,
        STOPPED,
        FINISHED;
    }

}
