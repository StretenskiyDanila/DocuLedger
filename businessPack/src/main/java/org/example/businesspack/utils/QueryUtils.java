package org.example.businesspack.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@UtilityClass
public class QueryUtils {

    public void toString(int num, PreparedStatement ps, String value) throws SQLException {
        if (value != null) {
            ps.setString(num, value);
        }
    }

    public void toInt(int num, PreparedStatement ps, Integer value) throws SQLException {
        if (value != null) {
            ps.setInt(num, value);
        }
    }

    public void toBigDecimal(int num, PreparedStatement ps, BigDecimal value) throws SQLException {
        if (value != null) {
            ps.setBigDecimal(num, value);
        }
    }

}
