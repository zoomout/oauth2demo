package com.bogdan.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by zoomout on 11/27/16.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "index.html";
    }

}
