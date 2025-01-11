package org.example.businesspack.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataWork extends Table {

    private Long id;
    private String group;
    private Integer count;
    private String name;
    private BigDecimal price;
    private BigDecimal summa;
    private String unitMeas;
    private BigDecimal vat;
    private String tab;

    @Override
    public String getTableName() {
        return "data_work";
    }

}
