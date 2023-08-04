package com.yemei.yewai.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestAndResponseTool {
	
	/**第二种简便方式获取*/
    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("http://www.baidu.com").get();
        Element body = document.body();
        Elements select = body.select("div[id=u1]").select( "a[href]");
        String text = select.text();
        //打印数据
        System.out.println(text);
    }
    
	/**第一种原始方式获取*/
    public Object sendRequest() throws Exception {
        //定义请求 URL 这里用百度举例
        URL url = new URL("http://www.baidu.com");
        //实例化对象，官方文档就是用这个方式实例化 HttpURLConnection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setReadTimeout(2000);
        //确认成功与否
        if (httpURLConnection.getResponseCode() == 200){
            //获取输入流
            InputStream inputStream = httpURLConnection.getInputStream();
            //获取 html 页面
            String html = convertStreamToString(inputStream);
            //拿到 Document
            Document document = Jsoup.parse(html);
            Element body = document.body();
            Elements select = body.select("div[id=u1]").select( "a[href]");
            //打印数据
            String text = select.text();
            System.out.println(text);
        }
        return null;
    }


    /**这个方法是将InputStream转化为String*/
    public static String convertStreamToString(InputStream is) throws IOException {
        if (is == null){
            return "";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reader.close();
        return sb.toString();
    }

}

