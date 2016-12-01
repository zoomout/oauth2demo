package com.bogdan.oauthclient.service.impl;

import com.bogdan.oauthclient.domain.Entity;
import com.bogdan.oauthclient.service.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by zoomout on 11/26/16.
 */


@Service
public class HttpServiceImpl implements HttpService {

    private static final String CLIENT_ID = "cl_id";
    private static final String CLIENT_SECRET = "client_secret";

    private String credential = "Basic " + Base64.getEncoder()
                                                 .encodeToString(
                                                     (CLIENT_ID + ":" + CLIENT_SECRET).getBytes());

    public JSONObject getToken(String authCode) throws IOException, JSONException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://localhost:8081/oauth/token");

        httpPost.setHeader("Authorization", credential);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", CLIENT_ID));
        params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/authCode"));
        params.add(new BasicNameValuePair("code", authCode));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = client.execute(httpPost);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
                                                                             .getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

        JSONObject jsonObject = new JSONObject(result.toString());

        client.close();

        return jsonObject;
    }

    public List<Entity> getResource(String token) throws IOException, JSONException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://localhost:8082/resource/entity/all");
        httpGet.setHeader("Authorization", "bearer " + token);
        CloseableHttpResponse response = client.execute(httpGet);

        return responseParser(response);

    }


    private List<Entity> responseParser(CloseableHttpResponse response)
        throws IOException, JSONException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
                                                                             .getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        List<Entity> entityList = new ArrayList<>();

        JSONArray jsonarray = new JSONArray(result.toString());

        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            Entity entity = mapper.readValue(jsonobject.toString(), Entity.class);
            entityList.add(entity);
        }

        return entityList;

    }

    public String getPublicInfo() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://localhost:8082/public/resource");

        CloseableHttpResponse response = client.execute(httpGet);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
                                                                             .getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }
}
