package com.yemei.yewai.schedule;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.yemei.yewai.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Description: 自动任务爬取百度
 * Author: junming.zhang
 * Date: 2023/08/07
 */
@Component
@Slf4j
public class CrawlerTask {

    @Scheduled(cron = "${scheduling.crawler.task1.cron}")
    public void task1() {
        HttpUtils u = new HttpUtils();
        JSONObject jsonObject = u.doPostJson(null, "https://api.vvhan.com/api/wbhot");
        // 处理返回结果
        JSONArray titles = jsonObject.getJSONArray("data");
        String title = "";
        for (int i = 0; i < titles.size(); i++) {
            JSONObject item = titles.getJSONObject(i);
            title = item.getString("title");
            Elements hotSearchList = null;

            JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
            List<SegToken> tokenList = jiebaSegmenter.process(title, JiebaSegmenter.SegMode.SEARCH);
            System.out.println(tokenList);
            for (SegToken token : tokenList) {
                String stringResponseEntity = u.doGet("https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=" + token.word);
                Document doc = Jsoup.parse(stringResponseEntity);
                hotSearchList = doc.select("#content_left .c-row .c-span-last .c-color-text");

                if (!hotSearchList.isEmpty()) {
                    // 遍历热搜列表并输出
                    for (Element hotSearch : hotSearchList) {
                        String hotSearchText = hotSearch.text();
                        log.info("hotSearch:" + hotSearchText);
                    }
                }

            }
        }
    }
}
