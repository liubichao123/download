package com.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class DownLoad {
    static String basePath = "http://www.f96.net";
    static String title = "韩三千";
    public static void main(String[] args) throws IOException {
        download();
    }
    static void download() throws IOException {
        Document document = Jsoup.connect(basePath + "/44/44336/").get();
        Element element = document.getElementsByClass("mulu_list").get(0);
        Elements listElement = element.getElementsByTag("li");
        for(int i = 449 ; i < listElement.size() ;i++){
            Element a = listElement.get(i).getElementsByTag("a").get(0);
            String href = a.attr("href");
            System.out.println((i + 1) + "----" + a.text() + "---" + href);
            String content = Jsoup.connect(basePath + href).timeout(30000).get().getElementById("htmlContent").text();
            content = a.text() + "\r\n" + content + "\r\n";
            write(content);
        }
    }

    static void write(String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("D:/lbc/"+ title +".txt"),true));
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
