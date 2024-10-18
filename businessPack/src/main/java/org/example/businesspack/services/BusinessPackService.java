package org.example.businesspack.services;

import org.example.businesspack.entities.Master;

public interface BusinessPackService {

    Master addMaster(Master master);

    Master getMaster(Long id);

    Master getMaster();

}
