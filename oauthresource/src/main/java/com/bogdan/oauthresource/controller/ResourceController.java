package com.bogdan.oauthresource.controller;

import com.bogdan.oauthresource.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bogdan.oauthresource.service.EntityService;

import java.util.List;

@RestController
public class ResourceController {

    @Autowired
    private EntityService entityService;

    @RequestMapping("/resource/entity/all")
    public List<Entity> findAll() {
        return entityService.findAll();
    }

}
