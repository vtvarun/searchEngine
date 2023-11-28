package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.*;

public class Crawler {
    HashSet<String> urlSet;
    int maxDepth = 2;

    Crawler(){
        urlSet = new HashSet<>();
    }

    public void readPages(String url, int depth){
        // if already visited : then do not revisit the url
        if(depth > maxDepth) return;

        // mark visited
        urlSet.add(url);

        try{
            Document document = Jsoup.connect(url).timeout(5000).get();
            System.out.println(document.title());
            Indexing indexing = new Indexing(document,url);
            Elements allLinksOnPage = document.select("a[href]");

            for(Element currLink : allLinksOnPage){
                String currUrl = currLink.attr("abs:href");
                if(!urlSet.contains(currUrl)){
                    readPages(currUrl,depth++);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Crawler crawl = new Crawler();
        crawl.readPages("https://www.javatpoint.com/",0);
    }
}