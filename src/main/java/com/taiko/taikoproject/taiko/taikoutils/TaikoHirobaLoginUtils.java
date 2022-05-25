package com.taiko.taikoproject.taiko.taikoutils;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.protocol.data.DataUrlDecoder;
import com.gargoylesoftware.htmlunit.util.Cookie;

import org.apache.tomcat.util.digester.DocumentProperties.Charset;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class TaikoHirobaLoginUtils {
    static DataUrlDecoder s;

    public String login(String naverId, String naverPw) throws Exception {

        WebClient wc = new WebClient();

        String url = "https://account.bandainamcoid.com/login.html?client_id=idportal&customize_id=&redirect_uri=";
        String redirect_uri = "https://www.bandainamcoid.com/v2/oauth2/auth?back=v3&client_id=&scope=&redirect_uri=&text=";
        try {
            HtmlPage page = (HtmlPage) wc.getPage(url + redirect_uri);
            page.getXmlEncoding();
            HtmlInput id = (HtmlInput) page.getHtmlElementById("mail");
            HtmlInput password = (HtmlInput) page.getHtmlElementById("pass");
            id.setValueAttribute(naverId);
            password.setValueAttribute(naverPw);
            final List<?> divs = page.getByXPath("//div");

            HtmlElement button = page.getFirstByXPath("//button[@class='btn _btn-size-50 _btn-yellow']");
            button.click();
            System.out.println(page.asNormalizedText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wc.close();
        }
        // return !currPage.asText().contains("Naver Sign in");
        return null;
    }
}