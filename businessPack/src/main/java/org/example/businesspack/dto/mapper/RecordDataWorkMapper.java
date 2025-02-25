package org.example.businesspack.dto.mapper;

import org.example.businesspack.bd.tables.records.DataWorkRecord;
import org.example.businesspack.dto.DataWorkDto;
import org.jooq.RecordMapper;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class RecordDataWorkMapper implements RecordMapper<DataWorkRecord, DataWorkDto> {

    @Override
    public DataWorkDto map(DataWorkRecord arg0) {
        if (arg0 == null) return null;
        var dto = DataWorkDto.builder()
            .id(new SimpleIntegerProperty(arg0.getId()))
            .name(new SimpleStringProperty(arg0.getName()))
            .count(new SimpleIntegerProperty(arg0.getCount()))
            .group(new SimpleStringProperty(arg0.getGroup()))
            .price(new SimpleObjectProperty<>(arg0.getPrice()))
            .summa(new SimpleObjectProperty<>(arg0.getSumma()))
            .vat(new SimpleObjectProperty<>(arg0.getVat()))
            .unitMeas(new SimpleStringProperty(arg0.getUnitMeas()))
            .tab(arg0.getTab())
        .build();

        dto.addProperty();
        return dto;
    }

}
