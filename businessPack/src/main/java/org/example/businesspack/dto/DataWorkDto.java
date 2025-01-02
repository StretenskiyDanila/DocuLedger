package org.example.businesspack.dto;

import lombok.*;
import org.example.businesspack.entities.DataWork;

@Data
@Builder
@AllArgsConstructor
public class DataWorkDto {

    private Long id;
    private String group;
    private String count;
    private String name;
    private String price;
    private String summa;
    private String unitMeas;
    private String vat;
    private String tab;

    public DataWorkDto(String tab) {
        group = "Не задано";
        count = "Не задано";
        name = "Не задано";
        price = "Не задано";
        summa = "Не задано";
        unitMeas = "Не задано";
        vat = "Не задано";
        this.tab = tab;
    }

    public static DataWorkDto of(DataWork account) {
        return DataWorkDto.builder()
                .id(account.getId())
                .count(account.getCount())
                .group(account.getGroup())
                .summa(account.getSumma())
                .vat(account.getVat())
                .price(account.getPrice())
                .unitMeas(account.getUnitMeas())
                .name(account.getName())
                .tab(account.getTab())
                .build();
    }

    public static DataWork to(DataWorkDto dataWorkDto) {
        return DataWork.builder()
                .id(dataWorkDto.getId())
                .unitMeas(dataWorkDto.getUnitMeas())
                .group(dataWorkDto.getGroup())
                .count(dataWorkDto.getCount())
                .price(dataWorkDto.getPrice())
                .name(dataWorkDto.getName())
                .vat(dataWorkDto.getVat())
                .summa(dataWorkDto.getSumma())
                .tab(dataWorkDto.getTab())
                .build();
    }

}
