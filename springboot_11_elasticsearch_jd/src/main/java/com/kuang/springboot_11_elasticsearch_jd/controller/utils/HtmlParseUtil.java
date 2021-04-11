package com.kuang.springboot_11_elasticsearch_jd.controller.utils;

import com.kuang.springboot_11_elasticsearch_jd.controller.pojo.content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {

      new HtmlParseUtil().HtmlParse("java").forEach(System.out::println);
    }

    public ArrayList<content> HtmlParse(String keyword) throws IOException {
        String url="https://search.jd.com/Search?keyword="+keyword;
        Document document=Jsoup.parse(new URL(url),3000);

        Element goodsList= document.getElementById("J_goodsList");
        Elements elements= goodsList.getElementsByTag("li");
        ArrayList<content> list=new ArrayList<content>();

        for(Element element:elements){
            String img=element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price=element.getElementsByClass("p-price").eq(0).text();
            String title=element.getElementsByClass("p-name").eq(0).text();
            if(img!=null&&title.length()!=0){
                content content=  new content(img,price,title);
                list.add(content);
            }

        }
        return list;
    }
}

