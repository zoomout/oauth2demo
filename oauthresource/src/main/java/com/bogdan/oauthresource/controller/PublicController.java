package com.bogdan.oauthresource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zoomout on 11/27/16.
 */

@RestController
@RequestMapping("/public")
public class PublicController {

    @RequestMapping("/resource")
    public String resource1() {
        return "This is public resource which is accessible without OAuth authorization";
    }
}
