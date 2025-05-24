package org.example.businesspack.services;

import org.example.businesspack.dto.DataWorkDto;
import org.jooq.Condition;

import java.util.List;

public interface DataWorkService {

    Integer save(DataWorkDto entity);
    List<DataWorkDto> get(Condition condition);
    void delete();
    void deleteAllByTab(String tabName);
    void delete(DataWorkDto entity);
    Integer update(DataWorkDto updateEntity);
    
}
