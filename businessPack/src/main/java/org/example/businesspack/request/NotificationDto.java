package org.example.businesspack.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.businesspack.request.enums.ChannelNotification;
import org.example.businesspack.request.enums.TabState;

@Builder
@Setter
@Getter
public class NotificationDto {

    private String userName;
    private String userMail;
    private String tabName;
    private TabState state;
    private ChannelNotification channel;
    private Boolean enable;

}
