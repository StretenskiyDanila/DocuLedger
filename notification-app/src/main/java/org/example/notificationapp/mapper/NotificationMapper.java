package org.example.notificationapp.mapper;

import org.example.notificationapp.dto.NotificationDto;
import org.example.notificationapp.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface NotificationMapper extends Mappable<Notification, NotificationDto> {

    @Mappings({
            @Mapping(source = "dto.state", target = "state")
    })
    Notification mapToEntity(NotificationDto dto);

}
