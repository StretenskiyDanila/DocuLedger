package org.example.businesspack.window.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    private String group;
    private String count;
    private String name;
    private String price;
    private String summa;
    private String unitMeas;
    private String vat;

}
