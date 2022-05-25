package com.taiko.taikoproject.taiko.taikoutils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class TaikoHirobaLoginUtils {

    public static Map cookies;
    private static final String URL_LOGIN = "https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com";

    private boolean isLogin;
    private WebClient webClient;
    private HtmlPage currPage;

    public String login(String naverId, String naverPw) throws Exception {
        WebClient webClient = new WebClient();
        try {
            HtmlPage page = (HtmlPage) webClient
                    .getPage("https://nid.naver.com/nidlogin.login?url=https%3A%2F%2Fmail.naver.com%2F");
            HtmlForm form = page.getFormByName("frmNIDLogin");
            form.getInputByName("id").setValueAttribute(naverId); // works fine
            form.getInputByName("pw").setValueAttribute(naverPw); // does not work
            final List<?> divs = page.getByXPath("//div");
            // divs.get(25);
            // divs.get(25)
            // get div which has a 'name' attribute of 'John'

            HtmlDivision div = (HtmlDivision) page.getByXPath("//button[@type='submit']/button").get(0);
            div.click();
            System.out.println(page.asNormalizedText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
        // return !currPage.asText().contains("Naver Sign in");
        return null;
    }
}