package com.yemei.yewai.crawl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Description: 获取微博热搜文章title、爬取百度内容
 * Author: junming.zhang
 * Date: 2023/8/01
 */
public class MyUtils {

    public JSONArray getTitles(){
        RestTemplate restTemplate = new RestTemplate();

        // 创建可以处理HTTPS请求的HttpComponentsClientHttpRequestFactory
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        // 调用HTTPS接口
        String response = restTemplate.postForObject("https://api.vvhan.com/api/wbhot",new HttpEntity<>( headers),String.class);
//        String result = response.getBody();

        // 处理返回结果
        JSONObject json = JSON.parseObject(response);
        JSONArray data= (JSONArray) json.get("data");
        return data;
    }

    public Elements getHotSearchList(String s){
        String url = "https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=" + s;
//        System.out.println(url);
        try {
            // 发送HTTP请求并获取HTML页面
            Document doc = Jsoup.connect(url).get();

            // 解析HTML页面中的热搜列表
            Elements hotSearchList = doc.select("#content_left .c-row .c-span-last .c-color-text");
            return hotSearchList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}