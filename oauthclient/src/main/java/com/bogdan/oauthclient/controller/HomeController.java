package com.bogdan.oauthclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zoomout on 11/26/16.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
