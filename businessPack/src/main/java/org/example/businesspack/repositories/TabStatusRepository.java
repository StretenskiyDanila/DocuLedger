package org.example.businesspack.repositories;

import org.jooq.DSLContext;

import static org.example.businesspack.bd.Tables.TAB_STATUS;

public class TabStatusRepository {

    public Integer update(DSLContext dsl, String tabName, String status) {
        return dsl.update(TAB_STATUS)
                .set(TAB_STATUS.STATUS, status)
                .where(TAB_STATUS.TAB_NAME.eq(tabName))
                .execute();
    }

    public String getStatus(DSLContext dsl, String tabName) {
         var fetch = dsl.selectFrom(TAB_STATUS)
                .where(TAB_STATUS.TAB_NAME.eq(tabName))
                .fetchOne();
        return fetch == null ? null : fetch.getStatus();
    }

    public String getById(DSLContext dsl, String tabName) {
        var fetch = dsl.selectFrom(TAB_STATUS)
                .where(TAB_STATUS.TAB_NAME.eq(tabName))
                .fetchOne();
        return fetch == null ? null : fetch.getStatus();
    }

}
