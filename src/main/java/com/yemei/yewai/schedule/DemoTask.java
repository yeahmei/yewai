package com.yemei.yewai.schedule;


import com.alibaba.fastjson.JSONArray;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.yemei.yewai.crawl.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.net.URLEncoder;
import java.net.URLDecoder;

/**
 * Description: Spring自动任务demo
 * Author: minghua.xie
 * Date: 2023/6/07/05
 */
@Component
@Slf4j
public class DemoTask{


    /**
     * cron的配置在yml，统一格式 scheduling.任务类型.任务名称.cron
     */
    @Scheduled(cron = "${scheduling.demo.task1.cron}")
    public void task1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("cronTask 当前时间：" + sdf.format(new Date()));
    }

    @Scheduled(cron = "${scheduling.demo.task2.cron}")
    public void task2() {
        MyUtils utils = new MyUtils();
        JSONArray titles = utils.getTitles();
        System.out.println(titles);
        List<String> list = JSONArray.parseArray(titles.toJSONString(), String.class);
        String title = "";
        for(int i=0;i<titles.size();i++) {
            title = (String)titles.getJSONObject(i).get("title");
//            System.out.println(title);
            Elements hotSearchList = null;
            try {
                JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
                List<SegToken> tokenList = jiebaSegmenter.process(title, JiebaSegmenter.SegMode.SEARCH);
                for (SegToken token : tokenList) {
                    hotSearchList = utils.getHotSearchList(URLEncoder.encode( token.word, "UTF-8" ));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(!hotSearchList.isEmpty()){
                // 遍历热搜列表并输出
                for (Element hotSearch : hotSearchList) {
                    String hotSearchText = hotSearch.text();
                    System.out.println(hotSearchText);
                }
                log.info("热搜文章：" + hotSearchList);
            }
        }
//        log.info("cronTask 当前时间：" + sdf.format(new Date()));
    }

    public static void main(String[] args) {
        MyUtils utils = new MyUtils();
        JSONArray titles = utils.getTitles();
        System.out.println(titles);
        List<String> list = JSONArray.parseArray(titles.toJSONString(), String.class);
        String title = "";
        for(int i=0;i<titles.size();i++) {
            title = (String)titles.getJSONObject(i).get("title");
//            System.out.println(title);
            Elements hotSearchList = null;
            try {
                JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
                List<SegToken> tokenList = jiebaSegmenter.process(title, JiebaSegmenter.SegMode.SEARCH);
                for (SegToken token : tokenList) {
                    hotSearchList = utils.getHotSearchList(URLEncoder.encode( token.word, "UTF-8" ));

                    if(!hotSearchList.isEmpty()){
                        // 遍历热搜列表并输出
                        for (Element hotSearch : hotSearchList) {
                            String hotSearchText = hotSearch.text();
                            System.out.println(hotSearchText);
                        }
                    }

                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
