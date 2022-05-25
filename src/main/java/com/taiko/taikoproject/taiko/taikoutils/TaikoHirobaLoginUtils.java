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
import com.gargoylesoftware.htmlunit.ScriptResult;
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
        wc.getOptions().setThrowExceptionOnScriptError(false);
        String url = "https://account.bandainamcoid.com/login.html?client_id=nbgi_taiko&customize_id=&redirect_uri=https%3A%2F%2Fwww.bandainamcoid.com%2Fv2%2Foauth2%2Fauth%3Fback%3Dv3%26client_id%3Dnbgi_taiko%26scope%3DJpGroupAll%26redirect_uri%3Dhttps%253A%252F%252Fdonderhiroba.jp%252Flogin_process.php%253Finvite_code%253D%2526abs_back_url%253D%2526location_code%253D%26text%3D&prompt=login";

        try {
            HtmlPage page = (HtmlPage) wc.getPage(url);
            HtmlInput id = (HtmlInput) page.getElementByName("mail");
            HtmlInput password = (HtmlInput) page.getElementByName("pass");
            id.setValueAttribute("dlwnghks6821@naver.com");
            password.setValueAttribute("lms3821su");
            System.out.println("입력한 id =>" + id.getValueAttribute() + "입력한 pw =>" + password.getValueAttribute());
            password.getValueAttribute();
            final List<?> divs = page.getByXPath("//div");

            // HtmlElement button = page.getFirstByXPath("//button[@class='btn _btn-size-50
            // _btn-gray']");
            // button.click();

            HtmlElement button2 = page.getFirstByXPath("//button[@class='btn _btn-size-50 _btn-gray']");
            button2.removeAttribute("disabled");
            button2.removeAllChildren();
            button2.removeStyleAttribute("disabled");

            button2.click();
            Thread.sleep(100);
            System.out.println(page.asNormalizedText());
            // System.out.println(page.getTextContent());

            HtmlPage page2 = wc.getPage("https://donderhiroba.jp/login_select.php");
            // System.out.println(page2.getTextContent());
            // HtmlImageInput inputSubmit = (HtmlImageInput) page2
            // .getFirstByXPath("//form[@class='buttonImage']");
            // inputSubmit.click();

            System.out.println(page2.asNormalizedText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // return !currPage.asText().contains("Naver Sign in");
        return null;
    }
}