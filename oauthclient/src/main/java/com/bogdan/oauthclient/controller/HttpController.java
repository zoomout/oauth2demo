package com.bogdan.oauthclient.controller;

import com.bogdan.oauthclient.domain.Entity;
import com.bogdan.oauthclient.service.HttpService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * Created by zoomout on 11/26/16.
 */

@Controller
public class HttpController {

    private static final String CLIENT_ID = "cl_id";
    private String authCode;
    private String token;
    @Autowired
    private HttpService httpService;

    @RequestMapping("/authCode")
    public String showAuthCode(
        @RequestParam("code")
            String authCode, Model model) {
        this.authCode = authCode;
        model.addAttribute("authCode", authCode);
        return "index";
    }

    @RequestMapping("/token")
    public String showToken(Model model) {
        model.addAttribute("token", token);
        return "index";
    }

    @RequestMapping("/getAuthCode")
    public String redirect() {
        return "redirect:http://localhost:8081/oauth/authorize?response_type=code&client_id="
            + CLIENT_ID + "&redirect_uri=http://localhost:8080/authCode";
    }

    @RequestMapping("/getToken")
    public String getToken(Model model) throws IOException {
        JSONObject jsonObject = httpService.getToken(this.authCode);

        this.token = (String) jsonObject.get("access_token");

        model.addAttribute("authCode", "Auth Code Expired");
        model.addAttribute("token", token);

        return "index";
    }

    @RequestMapping("/getResource")
    public String getResource(Model model) throws IOException {
        List<Entity> entityList = httpService.getResource(this.token);

        model.addAttribute("authCode", "Auth Code Expired");
        model.addAttribute("token", this.token);
        model.addAttribute("entityList", entityList);

        return "index";
    }

    @RequestMapping("/getPublicResource")
    public String getPublicResource(Model model) throws IOException {
        String sentence = httpService.getPublicInfo();
        model.addAttribute("sentence", sentence);

        return "index";
    }
}
