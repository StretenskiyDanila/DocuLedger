package org.example.notificationapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class Notification {

    private Long userId;
    private Set<TabTask> tabTaskSet;

}
