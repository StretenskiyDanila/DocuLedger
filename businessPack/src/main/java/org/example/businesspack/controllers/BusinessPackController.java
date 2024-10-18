package org.example.businesspack.controllers;

import lombok.RequiredArgsConstructor;
import org.example.businesspack.entities.Master;
import org.example.businesspack.services.BusinessPackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BusinessPackController {

    private final BusinessPackService businessPackService;

    @PostMapping("api/add/{name}")
    public String addMaster(@PathVariable(name = "name") String name) {
        Master master = Master.builder()
                .name(name)
                .position("Работник")
                .build();
        Master addMasters = businessPackService.addMaster(master);
        return addMasters == null ? "No" : name;
    }

    @GetMapping("api/get")
    public Master getMaster() {
        return businessPackService.getMaster();
    }

}
