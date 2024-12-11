package org.example.businesspack.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Table{

    private Long id;
    private String name;
    private String post;
    private PersonRole role;

    @Override
    public String getTableName() {
        return "person";
    }

}
