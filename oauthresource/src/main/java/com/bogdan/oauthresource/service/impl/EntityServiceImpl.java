package com.bogdan.oauthresource.service.impl;


import com.bogdan.oauthresource.domain.Entity;
import com.bogdan.oauthresource.domain.enam.Gender;
import com.bogdan.oauthresource.domain.enam.Status;
import com.bogdan.oauthresource.service.EntityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {

    public List<Entity> findAll() {
        List<Entity> entityList = new ArrayList<>();
        Entity entity1 = new Entity();
        entity1.setEntityId(1L);
        entity1.setFirstName("John");
        entity1.setLastName("Jackson");
        entity1.setGender(Gender.MALE);
        entity1.setLicenseNumber("PW123D");
        entity1.setAge("23");
        entity1.setStatus(Status.ACTIVE);
        entityList.add(entity1);

        Entity entity2 = new Entity();
        entity2.setEntityId(2L);
        entity2.setFirstName("Jack");
        entity2.setLastName("Johnson");
        entity2.setGender(Gender.MALE);
        entity2.setLicenseNumber("WP321D");
        entity2.setAge("32");
        entity2.setStatus(Status.ACTIVE);
        entityList.add(entity2);

        Entity entity3 = new Entity();
        entity3.setEntityId(3L);
        entity3.setFirstName("Alicia");
        entity3.setLastName("Vikander");
        entity3.setGender(Gender.FEMALE);
        entity3.setLicenseNumber("AV777W");
        entity3.setAge("28");
        entity3.setStatus(Status.ACTIVE);
        entityList.add(entity3);

        return entityList;
    }
}
