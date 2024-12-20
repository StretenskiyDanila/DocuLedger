package org.example.businesspack.services;

import java.util.List;

import org.example.businesspack.dto.DataWorkDto;

public interface DataWorkService {

    Long save(DataWorkDto entity);
    List<DataWorkDto> get();
    void delete(DataWorkDto entity);
    Long update(DataWorkDto updateEntity);

}
