package org.example.businesspack.dto;

import lombok.*;
import org.example.businesspack.entities.DataWork;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String group;
    private String count;
    private String name;
    private String price;
    private String summa;
    private String unitMeas;
    private String vat;

    public static AccountDto of(DataWork account) {
        return AccountDto.builder()
                .count(account.getCount())
                .group(account.getGroup())
                .summa(account.getSumma())
                .vat(account.getVat())
                .price(account.getPrice())
                .unitMeas(account.getUnitMeas())
                .name(account.getName())
                .build();
    }

    public static DataWork to(AccountDto accountDto) {
        return DataWork.builder()
                .unitMeas(accountDto.getUnitMeas())
                .group(accountDto.getGroup())
                .count(accountDto.getCount())
                .price(accountDto.getPrice())
                .name(accountDto.getName())
                .vat(accountDto.getVat())
                .summa(accountDto.getSumma())
                .build();
    }

}
