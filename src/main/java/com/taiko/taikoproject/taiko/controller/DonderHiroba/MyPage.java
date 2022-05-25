package com.taiko.taikoproject.taiko.controller.DonderHiroba;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPage {
    @GetMapping("/myPage")
    public String myPage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        String url = "https://donderhiroba.jp/mypage_kisekae.php";
        WebClient wc = new WebClient();
        wc.getOptions().setThrowExceptionOnScriptError(false);
        try {
            HtmlPage page = (HtmlPage) wc.getPage(url);
            System.out.println(page.asNormalizedText());

        } catch (Error e) {
            e.printStackTrace();
        }
        return null;
    }

}
