package com.study.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: wenqianqian
 * @Desc: 一些发送请求的工具类
 */
public class UrlUtil {

    private UrlUtil(){}

    /**
     * 发送post请求
     * @param restTemplate
     * @param headerMap 请求头参数
     * @param url 地址
     * @param paramsList 参数
     * @param tClass 返回类型
     * @param <T>
     * @return
     */
    public static <T> T postListForEntity(RestTemplate restTemplate, Map<String,String> headerMap, String url, List<Map<String,Object>> paramsList, Class<T> tClass) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if(!Objects.isNull(headerMap)){
            for (Map.Entry<String, String> entry : headerMap.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(paramsList, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tClass);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        } else {
            //抛出异常
            throw new Exception("接口请求失败");

        }
    }

    /**
     * 发送post请求
     * @param restTemplate
     * @param headerMap 请求头参数
     * @param url 地址
     * @param params 参数
     * @param tClass 返回类型
     * @param <T>
     * @return
     */
    public static <T> T postForEntity(RestTemplate restTemplate, Map<String,String> headerMap, String url, Map<String,Object> params, Class<T> tClass) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if(!Objects.isNull(headerMap)){
            for (Map.Entry<String, String> entry : headerMap.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tClass);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        } else {
            //抛出异常
            throw new Exception("接口请求失败");
        }
    }

    /**
     * 发送get请求
     * @param restTemplate
     * @param headerMap 请求头参数
     * @param url 地址
     * @param params 参数
     * @param tClass 返回类型
     * @param <T>
     * @return
     */
    public static <T> T getForEntity(RestTemplate restTemplate, Map<String,String> headerMap, String url, Map<String,Object> params, Class<T> tClass) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if(!Objects.isNull(headerMap)){
            for (Map.Entry<String, String> entry : headerMap.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = uriComponentsBuilder.build(true).toUri();
        ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, tClass);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        } else {
            //抛出异常
            throw new Exception("接口请求失败");
        }
    }

    /**
     * 发送put请求
     * @param restTemplate
     * @param headerMap 请求头参数
     * @param url 地址
     * @param params 参数
     * @param tClass 返回类型
     * @param <T>
     * @return
     */
    public static <T> T putForEntity(RestTemplate restTemplate, Map<String,String> headerMap, String url, Map<String,Object> params, Class<T> tClass) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if(!Objects.isNull(headerMap)){
            for (Map.Entry<String, String> entry : headerMap.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, tClass);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        } else {
            //抛出异常
            throw new Exception("接口请求失败");
        }
    }

    /**
     * 发送delete请求
     * @param restTemplate
     * @param headerMap 请求头参数
     * @param url 地址
     * @param params 参数
     * @param tClass 返回类型
     * @param <T>
     * @return
     */
    public static <T> T deleteForEntity(RestTemplate restTemplate, Map<String,String> headerMap, String url, Map<String,Object> params, Class<T> tClass) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if(!Objects.isNull(headerMap)){
            for (Map.Entry<String, String> entry : headerMap.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request, tClass);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        } else {
            //抛出异常
            throw new Exception("接口请求失败");
        }
    }


}
