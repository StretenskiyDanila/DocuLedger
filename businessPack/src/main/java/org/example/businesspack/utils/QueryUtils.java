package org.example.businesspack.utils;

import lombok.experimental.UtilityClass;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@UtilityClass
public class QueryUtils {

    public void toString(int num, PreparedStatement ps, String value) throws SQLException {
        if (value != null) {
            ps.setString(num, value);
        }
    }

}
