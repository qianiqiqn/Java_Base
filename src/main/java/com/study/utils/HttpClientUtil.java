package com.study.utils;

import com.alibaba.fastjson2.JSONObject;
import com.study.classin.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author amy
 * @description TODO
 * @date 2023/7/17 15:37
 */
@Slf4j
public class HttpClientUtil {

    public static String doGet(String url, String charset, List<Header> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = null;
        //接收返回值
        CloseableHttpResponse response = null;
        String content = null;
        try {
            //请求执行
            httpGet= new HttpGet(url);
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    httpGet.addHeader(header);
                }
            }
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        }catch (Exception e){
            log.error("请求失败：{}",e.getMessage());
        } finally {
            try {
                if(response!=null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, JSONObject params, List<Header> headers){
        //创建httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http post
        HttpPost httpPost = new HttpPost(url);
        //模拟浏览器设置头
        httpPost.setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
        httpPost.addHeader("Content-Type", "application/json");
        if (!CollectionUtils.isEmpty(headers)) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }
        //将表达请求放入到httpost
        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));
        //返回类型
        CloseableHttpResponse response = null;
        String content = null;
        try {
            response = httpClient.execute(httpPost);
            content = EntityUtils.toString(response.getEntity(), "utf-8");
        }catch (Exception e){
            log.info("url:" + url);
            log.info("Entity:" + httpPost.getEntity().toString());
            log.info("heads:" + httpPost.getAllHeaders().toString());
            log.error("请求失败：{}"+e.getMessage(),e);
            throw new BusinessException("999","请求失败："+e.getMessage());
        }finally {
            try {
                if(response!=null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       return content;
    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public static String doPostSsl(String url, JSONObject params, List<Header> headers){
        //创建httpclient
        CloseableHttpClient httpClient = buildDefaultHttpClientTrustSSL();
        //创建http post
        HttpPost httpPost = new HttpPost(url);
        //模拟浏览器设置头
        httpPost.setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
        httpPost.addHeader("Content-Type", "application/json");
        if (!CollectionUtils.isEmpty(headers)) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }
        //将表达请求放入到httpost
        httpPost.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));
        //返回类型
        CloseableHttpResponse response = null;
        String content = null;
        try {
            response = httpClient.execute(httpPost);
            content = EntityUtils.toString(response.getEntity(), "utf-8");
        }catch (Exception e){
            log.info("url:" + url);
            log.info("Entity:" + httpPost.getEntity().toString());
            log.info("heads:" + httpPost.getAllHeaders().toString());
            log.error("请求失败：{}"+e.getMessage(),e);
            throw new BusinessException("999","请求失败："+e.getMessage());
        }finally {
            try {
                if(response!=null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 信任SSL证书
     * @return
     */
    public static CloseableHttpClient buildDefaultHttpClientTrustSSL()
    {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL).loadTrustMaterial((x, y) -> true).build();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            e.printStackTrace();
        }
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .setContentCompressionEnabled(true)
                .build();
        return HttpClientBuilder.create().setDefaultRequestConfig(config).setSSLContext(sslContext).setSSLHostnameVerifier((x, y) -> true).build();
    }

    public static CloseableHttpClient buildDefaultHttpClient()
    {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .setContentCompressionEnabled(true)
                .setStaleConnectionCheckEnabled(true)
                .build();

        return HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
    }

}
