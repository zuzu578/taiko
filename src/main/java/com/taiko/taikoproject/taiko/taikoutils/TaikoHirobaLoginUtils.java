package com.taiko.taikoproject.taiko.taikoutils;

import java.util.HashMap;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import com.gargoylesoftware.htmlunit.protocol.data.DataUrlDecoder;

public class TaikoHirobaLoginUtils {
    static DataUrlDecoder s;

    public String login(String naverId, String naverPw) throws Exception {
        // HashMap<String, Object> userInfo = new HashMap<String, Object>();
        WebClient wc = new WebClient();
        String template = "";

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

            HtmlElement button2 = page.getFirstByXPath("//button[@class='btn _btn-size-50 _btn-gray']");
            button2.removeAttribute("disabled");
            button2.removeAllChildren();
            button2.removeStyleAttribute("disabled");

            button2.click();
            // 필수!
            Thread.sleep(5000);

            HtmlPage page2 = wc.getPage("https://donderhiroba.jp/login_select.php");
            System.out.println(page2.asNormalizedText());

            Thread.sleep(3000);
            HtmlAnchor button3 = page2.getAnchorByHref("javascript:void(0)");
            button3.click();
            // 유저 정보 페이지
            HtmlPage page3 = wc.getPage("https://donderhiroba.jp/index.php");
            // List<HtmlDivision> myDonImg =
            // page3.getByXPath("//img[@class='customd_mydon']");
            // userInfo.put("myDonImag", myDonImg.get(0));

            // final List<?> divs = page.getByXPath("//div");
            // for (int i = 0; i < divs.size(); i++) {
            // System.out.println(divs.get(i));
            // }
            // List<HtmlDivision> myDongInfo = page3.getByXPath("//div[@id='mydon_area']");
            // for (int i = 0; i < myDongInfo.size(); i++) {
            // System.out.println(myDongInfo.get(i));
            // }
            template = page3.asXml();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
}