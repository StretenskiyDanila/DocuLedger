package org.example.businesspack.utils;

import lombok.experimental.UtilityClass;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.UserDataDto;
import org.jooq.DSLContext;
import org.jooq.Record;

import static org.example.businesspack.bd.Tables.*;

@UtilityClass
public class Builder {

	public Record to(DSLContext dsl, DataWorkDto dto) {
        var record = dsl.newRecord(DATA_WORK.NAME, DATA_WORK.UNIT_MEAS, DATA_WORK.COUNT, DATA_WORK.PRICE,
        DATA_WORK.VAT, DATA_WORK.SUMMA, DATA_WORK.GROUP, DATA_WORK.TAB);
        record.set(DATA_WORK.NAME, dto.getNameParameter());
        record.set(DATA_WORK.COUNT, dto.getCountParameter());
        record.set(DATA_WORK.GROUP, dto.getGroupParameter());
        record.set(DATA_WORK.PRICE, dto.getPriceParameter());
        record.set(DATA_WORK.SUMMA, dto.getSummaParameter());
        record.set(DATA_WORK.UNIT_MEAS, dto.getUnitMeasParameter());
        record.set(DATA_WORK.VAT, dto.getVatParameter());
        record.set(DATA_WORK.TAB, dto.getTab());
        return record;
    }

	public Record to(DSLContext dsl, PersonDto dto) {
        var record = dsl.newRecord(PERSON.NAME, PERSON.ROLE, PERSON.TAB);
        record.set(PERSON.NAME, dto.getName());
        record.set(PERSON.ROLE, dto.getRole().getName());
        record.set(PERSON.TAB, dto.getTab());
        return record;
    }

    public Record to(DSLContext dsl, UserDataDto dto) {
        var record = dsl.newRecord(USER_DATA.TELEGRAM_NAME, USER_DATA.EMAIL);
        record.set(USER_DATA.TELEGRAM_NAME, dto.getTelegramName());
        record.set(USER_DATA.EMAIL, dto.getEmail());
        return record;
    }

}
