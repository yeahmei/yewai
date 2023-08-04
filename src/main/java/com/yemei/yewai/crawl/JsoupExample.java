package com.yemei.yewai.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupExample {
    public static void main(String[] args) {
        try {
//            String url = "http://www.baidu.com";
//            Document document = Jsoup.connect(url).get();
//            String html = document.html();
//            System.out.println(html);
            Document document = Jsoup.connect("http://www.baidu.com").get();
            Element body = document.body();
            Elements select = body.select("div[id=u1]").select( "a[href]");
            String text = select.text();
            //打印数据
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
