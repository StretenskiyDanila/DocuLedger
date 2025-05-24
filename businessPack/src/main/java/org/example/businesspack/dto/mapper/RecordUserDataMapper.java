package org.example.businesspack.dto.mapper;

import org.example.businesspack.bd.tables.records.UserDataRecord;
import org.example.businesspack.dto.UserDataDto;
import org.jooq.RecordMapper;

public class RecordUserDataMapper implements RecordMapper<UserDataRecord, UserDataDto> {

    @Override
    public UserDataDto map(UserDataRecord arg0) {
        if (arg0 == null) return null;
        return UserDataDto.builder()
                .telegramName(arg0.getTelegramName())
                .email(arg0.getEmail())
                .build();
    }

}
