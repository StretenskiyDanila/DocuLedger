package org.example.notificationapp.mapper;

public interface Mappable<E, D> {

    E mapToEntity(D dto);
    D mapToDto(E entity);

}
