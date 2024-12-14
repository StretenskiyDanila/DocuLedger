package org.example.businesspack.dto;

import lombok.*;
import org.example.businesspack.entities.DataWork;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataWorkDto {

    private String group;
    private String count;
    private String name;
    private String price;
    private String summa;
    private String unitMeas;
    private String vat;

    public static DataWorkDto of(DataWork account) {
        return DataWorkDto.builder()
                .count(account.getCount())
                .group(account.getGroup())
                .summa(account.getSumma())
                .vat(account.getVat())
                .price(account.getPrice())
                .unitMeas(account.getUnitMeas())
                .name(account.getName())
                .build();
    }

    public static DataWork to(DataWorkDto dataWorkDto) {
        return DataWork.builder()
                .unitMeas(dataWorkDto.getUnitMeas())
                .group(dataWorkDto.getGroup())
                .count(dataWorkDto.getCount())
                .price(dataWorkDto.getPrice())
                .name(dataWorkDto.getName())
                .vat(dataWorkDto.getVat())
                .summa(dataWorkDto.getSumma())
                .build();
    }

}
