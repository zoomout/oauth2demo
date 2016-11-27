package com.bogdan.authserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by zoomout on 11/27/16.
 */

@RestController
public class ResourceController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
