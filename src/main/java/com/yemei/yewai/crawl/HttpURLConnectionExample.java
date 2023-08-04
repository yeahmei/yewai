package com.yemei.yewai.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpURLConnectionExample {
    public static void main(String[] args) {
        try {
            // 定义请求的URL
            URL url = new URL("http://www.baidu.com");

            // 打开URL连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间为1秒
            connection.setConnectTimeout(1000);
            // 设置读取超时时间为2秒

            connection.setReadTimeout(2000);

            // 获取响应码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
//                // 获取输入流并包装成BufferedReader
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String line;
//                StringBuilder response = new StringBuilder();
//                // 读取响应内容并拼接到StringBuilder中
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
                // 关闭流
//                reader.close();
//                // 打印响应内容
//                System.out.println(response.toString());
                //获取输入流
                InputStream inputStream = connection.getInputStream();
                //获取 html 页面
                String html = convertStreamToString(inputStream);
                //拿到 Document
                Document document = Jsoup.parse(html);
                Element body = document.body();
                Elements select = body.select("div[id=u1]").select( "a[href]");
                //打印数据
                String text = select.text();
                System.out.println(text);
            } else {
                // 打印请求失败的信息和响应码
                System.out.println("Failed to get the response. Response code: " + responseCode);
            }

            // 断开连接
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
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