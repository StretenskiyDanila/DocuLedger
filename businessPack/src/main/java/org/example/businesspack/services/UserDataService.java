package org.example.businesspack.services;

import lombok.extern.slf4j.Slf4j;
import org.example.businesspack.configs.InitializeDatabase;
import org.example.businesspack.dto.UserDataDto;
import org.example.businesspack.repositories.UserDataRepository;
import org.jooq.DSLContext;
import org.jooq.Name;
import org.jooq.impl.DSL;

import java.util.Map;

import static org.example.businesspack.bd.Tables.USER_DATA;
import static org.example.businesspack.logs.LogMessage.*;

@Slf4j
public class UserDataService {

    private final DSLContext dsl = InitializeDatabase.dslContext;
    private final UserDataRepository userDataRepository = new UserDataRepository();

//    private final static List<TableField<UserDataRecord, ?>> columns = List.of(USER_DATA.TELEGRAM_NAME, USER_DATA.EMAIL);

    public UserDataDto getById(Integer id) {
        String methodName = "getById()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), USER_DATA.ID.eq(id));

        var result = userDataRepository.get(dsl, USER_DATA.ID.eq(id))
                .orElseThrow();
        log.debug(RESULT_METHOD.getMessage(), result);

        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

    public Integer update(UserDataDto entity) {
        String methodName = "update()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), entity);

        Map<Name, ?> setMap = Map.of(
                DSL.name("telegram_name"), entity.getTelegramName(),
                DSL.name("email"), entity.getEmail());

        Integer result = userDataRepository.update(dsl, setMap, USER_DATA.ID.eq(1));
        log.debug("Since the object exists, we update it.");

        log.debug(RESULT_METHOD.getMessage(), result);
        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

}
