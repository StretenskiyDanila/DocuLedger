package org.example.businesspack.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDataDto {

    private String telegramName;
    private String email;

}
