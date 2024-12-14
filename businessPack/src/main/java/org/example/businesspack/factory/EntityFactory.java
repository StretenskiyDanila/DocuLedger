package org.example.businesspack.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityFactory<T> {

    T create(ResultSet resultSet) throws SQLException;

}
