package org.example.businesspack.dto;

import lombok.Builder;
import lombok.Data;
import org.example.businesspack.entities.Master;

@Builder
@Data
public class MasterDto {

    private Long id;
    private String name;
    private String position;

    public static MasterDto of(Master master) {
        return MasterDto.builder()
                .id(master.getId())
                .name(master.getName())
                .position(master.getPost())
                .build();
    }

}
