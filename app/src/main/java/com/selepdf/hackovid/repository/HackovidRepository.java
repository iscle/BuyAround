package com.selepdf.hackovid.repository;

import com.selepdf.hackovid.service.HackovidService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HackovidRepository {

    private HackovidService service;

    @Inject
    public HackovidRepository(HackovidService service) {
        this.service = service;
    }
}
