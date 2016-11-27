package com.bogdan.oauthclient.service;

import com.bogdan.oauthclient.domain.Entity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by zoomout on 11/26/16.
 */
public interface HttpService {

    JSONObject getToken(String authCode) throws IOException, JSONException;

    List<Entity> getResource(String token) throws IOException, JSONException;

    String getPublicInfo() throws IOException;
}
