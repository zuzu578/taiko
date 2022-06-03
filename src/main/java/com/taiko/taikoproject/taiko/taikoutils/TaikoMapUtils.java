package com.taiko.taikoproject.taiko.taikoutils;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TaikoMapUtils {
    public void getTaikoMap() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        String url = "https://namu.wiki/w/%EC%8B%A0%20%ED%83%9C%EA%B3%A0%EC%9D%98%20%EB%8B%AC%EC%9D%B8/%EC%98%A4%EB%9D%BD%EC%8B%A4%20%EB%B6%84%ED%8F%AC";

        try {

            WebClient wc = new WebClient();
            wc.getOptions().setThrowExceptionOnScriptError(false);
            HtmlPage page = (HtmlPage) wc.getPage(url);
            Thread.sleep(4000);
            System.out.println("page" + page);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
