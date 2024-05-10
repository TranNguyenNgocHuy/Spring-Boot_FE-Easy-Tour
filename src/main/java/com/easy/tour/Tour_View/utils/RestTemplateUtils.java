package com.easy.tour.Tour_View.utils;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestTemplateUtils {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GetJwtToken getJwtToken;

    /**
     * @return
     */
    public HttpEntity<?> setHeaderDefault(Object data, HttpServletRequest request) {
        String jwtToken = getJwtToken.extractTokenFromCookie(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + jwtToken);

        return data == null ? new HttpEntity<>(headers) : new HttpEntity<>(data, headers);
    }

    /**
     * Get data from url and default no header
     * @param url
     * @param clazz
     * @return
     */
    public <T> T getData(String url, HttpServletRequest request, Class<?> clazz) {
        return getData(setHeaderDefault(null, request), url, clazz);
    }

    /**
     * Get data from url
     * @param entity
     * @param url
     * @param clazz
     * @return
     */
    public <T> T getData(HttpEntity<?> entity, String url, Class<?> clazz) {
        String data = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        Gson gson = new Gson();
        return (T) gson.fromJson(data, clazz);
    }

    /**
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T postData(Object data, String url, HttpServletRequest request, Class<?> clazz) {
        return postData(setHeaderDefault(data, request), url, clazz);
    }

    /**
     * @param entity
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T postData(HttpEntity<?> entity, String url, Class<?> clazz) {
        String data = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
        Gson gson = new Gson();

        return (T) gson.fromJson(data, clazz);
    }

    /**
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T putData(Object data, String url, HttpServletRequest request,Class<?> clazz) {
        return putData(setHeaderDefault(data, request), url, clazz);
    }

    /**
     * @param entity
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T putData(HttpEntity<?> entity, String url, Class<?> clazz) {
        String data = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class).getBody();
        Gson gson = new Gson();

        return (T) gson.fromJson(data, clazz);
    }

    /**
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T deleteData(Object data, String url, HttpServletRequest request,Class<?> clazz) {
        return deleteData(setHeaderDefault(data, request), url, clazz);
    }

    /**
     * @param entity
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T deleteData(HttpEntity<?> entity, String url, Class<?> clazz) {
        String data = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class).getBody();
        Gson gson = new Gson();

        return (T) gson.fromJson(data, clazz);
    }
    //end
}
