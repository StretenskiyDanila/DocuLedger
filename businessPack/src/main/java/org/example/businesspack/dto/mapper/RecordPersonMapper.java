package org.example.businesspack.dto.mapper;

import org.example.businesspack.bd.tables.records.PersonRecord;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.enums.PersonRole;
import org.jooq.RecordMapper;

public class RecordPersonMapper implements RecordMapper<PersonRecord, PersonDto> {

    @Override
    public PersonDto map(PersonRecord arg0) {
        if (arg0 == null) return null;
        return PersonDto.builder()
            .id(arg0.getId())
            .name(arg0.getName())
            .role(PersonRole.valueOf(arg0.getRole().toUpperCase()))
            .lastUsed(arg0.getLastUsed())
            .usageCount(arg0.getUsageCount())
            .tab(arg0.getTab())
        .build();
    }

}