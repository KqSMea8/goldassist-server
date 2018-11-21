package com.sutian.goldassist.common.http;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @author sutian
 * @email sijin.zsj@alibaba-inc.com
 * @create 2018/11/16 下午4:08
 */
public class HttpClientProxy {

    private final static int socketTimeout = 60000;
    private final static int connectTimeout = 60000;
    private final static int connectionRequestTimout = 60000;
    private final static int poolSize = 50;
    private final static HttpClient httpClient;

    private final static int HTTP_CODE_SUCEESS = 200;

    static {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimout).build();

        httpClient = HttpClientBuilder.create().setMaxConnTotal(poolSize)
                .setMaxConnPerRoute(poolSize).setDefaultRequestConfig(requestConfig).build();
    }

    public static String doGet(String url, Map<String, String> headerMap,Map<String, String> map, String charset,Boolean isGzip){
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        if (!CollectionUtils.isEmpty(map)) {
            url = fillGetUrl(url,map);
        }
        httpGet = new HttpGet(url);
        if(!headerMap.isEmpty()){
            for(Map.Entry<String,String> entry : headerMap.entrySet()){
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }
        try{
            HttpResponse response = httpClient.execute(httpGet);
            result = handleResponse(response,charset,isGzip);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String doPost(String url,Map<String, String> headerMap, Map<String, String> map, String charset, Boolean isGzip){
        HttpPost httpPost = null;
        String result = null;
        try{
            httpPost = new HttpPost(url);
            if(!headerMap.isEmpty()){
                for(Map.Entry<String,String> entry : headerMap.entrySet()){
                    httpPost.setHeader(entry.getKey(),entry.getValue());
                }
            }
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            result = handleResponse(response,charset,isGzip);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    private static String handleResponse(HttpResponse response,String charset, Boolean gzip) throws Exception {
        if(response == null){
            return "";
        }
        if(StringUtils.isEmpty(charset)){
            charset = "utf-8";
        }
        if(gzip){
            StringBuffer sb = new StringBuffer();
            InputStream stream = response.getEntity().getContent();
            InputStream dad = new GZIPInputStream(stream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(dad,charset));
            String line = "";
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        }else {
            return EntityUtils.toString(response.getEntity(), charset);
        }
    }

    private static String fillGetUrl(String url, Map<String, String> map) {
        StringBuffer sb = new StringBuffer(url);
        sb.append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue())) {
                try {
                    sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().trim(), "UTF-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
        return sb.toString();
    }

}
