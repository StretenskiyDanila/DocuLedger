package org.example.businesspack.window.models.table.converter;

import javafx.util.StringConverter;

public class IntegerConverter extends StringConverter<Integer> {

    @Override
    public Integer fromString(String arg0) {
        try {
            return Integer.parseInt(arg0);
        } catch (NumberFormatException e) {
            return 0; //TODO: Сделать обработку и вывода ошибки на экран
        }   
    }
    
    @Override
    public String toString(Integer arg0) {
        return arg0 != null ? arg0.toString() : "";
    }

}
