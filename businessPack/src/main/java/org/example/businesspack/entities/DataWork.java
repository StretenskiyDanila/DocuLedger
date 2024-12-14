package org.example.businesspack.entities;

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
    private String count;
    private String name;
    private String price;
    private String summa;
    private String unitMeas;
    private String vat;

    @Override
    public String getTableName() {
        return "data_work";
    }

}
