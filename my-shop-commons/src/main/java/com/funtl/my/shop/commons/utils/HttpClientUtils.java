package com.funtl.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3676.400 QQBrowser/10.4.3469.400";

    /**
     * get请求
     */
    public static String doGet(String url) {
        return createRequest(url,GET,null);
    }
  /**
     * get请求
     */
    public static String doGet(String url,String cookie) {
        return createRequest(url,GET,cookie);
    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url,BasicNameValuePair... params) {
        return createRequest(url,POST,null,params);
    }

    /**
     * post请求
     * @param url
     * @param cookie
     * @param params
     * @return
     */
    public static String doPost(String url,String cookie,BasicNameValuePair... params) {
        return createRequest(url,POST,cookie,params);
    }

    /**
     *创建请求
     * @param url 请求地址
     * @param requestMethod 请求方式GET/POST
     * @param cookie cookie
     * @param params 请求参数
     * @return
     */
    private static String createRequest(String url, String requestMethod,String cookie, BasicNameValuePair... params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //请求结果
        String result = null;
//        请求方式
        try {
            HttpGet httpGet = null;
            HttpPost httpPost = null;

            //响应
            CloseableHttpResponse httpResponse = null;

            //GET请求
            if (GET.equals(requestMethod)) {
                // 创建 HttpGet 请求
                httpGet = new HttpGet(url);
                // 设置长连接
                httpGet.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                // 设置代理（模拟浏览器版本）
                httpGet.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                // 设置 Cookie
                httpGet.setHeader("Cookie", cookie);

                httpResponse = httpClient.execute(httpGet);


            }
    //        POST请求
            else if (POST.equals(requestMethod)) {
                // 创建 HttpPost 请求
                httpPost = new HttpPost(url);
                // 设置长连接
                httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                // 设置代理（模拟浏览器版本）
                httpPost.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                // 设置 Cookie
                httpPost.setHeader("Cookie", cookie);

                if (params != null && params.length > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));

                }
                httpResponse = httpClient.execute(httpPost);
            }


            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }
}


