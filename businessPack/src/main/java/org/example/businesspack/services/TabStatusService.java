package org.example.businesspack.services;

import lombok.extern.slf4j.Slf4j;
import org.example.businesspack.configs.InitializeDatabase;
import org.example.businesspack.repositories.TabStatusRepository;
import org.example.businesspack.request.enums.TabState;
import org.jooq.DSLContext;

import static org.example.businesspack.logs.LogMessage.*;

@Slf4j
public class TabStatusService {

    private final DSLContext dsl = InitializeDatabase.dslContext;
    private final TabStatusRepository tabStatusRepository = new TabStatusRepository();

    public TabState getStatus(String tabName) {
        String methodName = "getStatus()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), tabName);

        var result = tabStatusRepository.getStatus(dsl, tabName);
        log.debug(RESULT_METHOD.getMessage(), result);

        log.info(END_METHOD.getMessage(), methodName);
        return TabState.valueOf(result);
    }

    public Integer update(String tabName, TabState status) {
        String methodName = "update()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), tabName + ", " + status);

        Integer result = tabStatusRepository.update(dsl, tabName, status.name());
        log.debug("Since the object exists, we update it.");

        log.debug(RESULT_METHOD.getMessage(), result);
        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }
    
}
