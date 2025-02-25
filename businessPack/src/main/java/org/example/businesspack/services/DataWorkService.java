package org.example.businesspack.services;

import java.util.List;

import org.example.businesspack.dto.DataWorkDto;
import org.jooq.Condition;

public interface DataWorkService {

    Integer save(DataWorkDto entity);
    List<DataWorkDto> get(Condition condition);
    void delete();
    void delete(DataWorkDto entity);
    Integer update(DataWorkDto updateEntity);
    
}
