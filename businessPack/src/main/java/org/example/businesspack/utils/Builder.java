package org.example.businesspack.utils;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.jooq.DSLContext;
import org.jooq.Record;

import lombok.experimental.UtilityClass;

import static org.example.businesspack.bd.Tables.DATA_WORK;
import static org.example.businesspack.bd.Tables.PERSON;;

@UtilityClass
public class Builder {

	public Record to(DSLContext dsl, DataWorkDto dataWorkDto) {
        var record = dsl.newRecord(DATA_WORK.NAME, DATA_WORK.UNIT_MEAS, DATA_WORK.COUNT, DATA_WORK.PRICE,
        DATA_WORK.VAT, DATA_WORK.SUMMA, DATA_WORK.GROUP, DATA_WORK.TAB);
        record.set(DATA_WORK.NAME, dataWorkDto.getNameParameter());
        record.set(DATA_WORK.COUNT, dataWorkDto.getCountParameter());
        record.set(DATA_WORK.GROUP, dataWorkDto.getGroupParameter());
        record.set(DATA_WORK.PRICE, dataWorkDto.getPriceParameter());
        record.set(DATA_WORK.SUMMA, dataWorkDto.getSummaParameter());
        record.set(DATA_WORK.UNIT_MEAS, dataWorkDto.getUnitMeasParameter());
        record.set(DATA_WORK.VAT, dataWorkDto.getVatParameter());
        record.set(DATA_WORK.TAB, dataWorkDto.getTab()); 
        return record;
    }

	public Record to(DSLContext dsl, PersonDto personDto) {
        var record = dsl.newRecord(PERSON.NAME, PERSON.ROLE, PERSON.TAB);
        record.set(PERSON.NAME, personDto.getName());
        record.set(PERSON.ROLE, personDto.getRole().getName());
        record.set(PERSON.TAB, personDto.getTab());
        return record;
    }

}
