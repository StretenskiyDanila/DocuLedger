package org.example.businesspack.window.models.table.converter;

import java.math.BigDecimal;

import javafx.util.StringConverter;

public class BigDecimalConverter extends StringConverter<BigDecimal> {

    @Override
    public String toString(BigDecimal object) {
        return object != null ? object.toPlainString() : "";
    }

    @Override
    public BigDecimal fromString(String string) {
        try {
            return new BigDecimal(string);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO; //TODO: Сделать обработку и вывода ошибки на экран
        }
    }

}
