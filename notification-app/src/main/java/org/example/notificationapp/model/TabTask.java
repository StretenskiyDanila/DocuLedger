package org.example.notificationapp.model;

import lombok.Builder;
import org.example.notificationapp.model.enums.StateEnum;

@Builder
public class TabTask {

    private String tabName;
    private StateEnum state;

}
