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
import com.taiko.taikoproject.taiko.controller.DonderHiroba.DonderHirobaLogin;
import com.taiko.taikoproject.taikoVO.DonderHirobaLoginParam;

public class TaikoHirobaLoginUtils {

    public HashMap<String, Object> login(DonderHirobaLoginParam loginvo) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        WebClient wc = new WebClient();
        String template = "";

        String url = "https://account.bandainamcoid.com/login.html?client_id=nbgi_taiko&customize_id=&redirect_uri=https%3A%2F%2Fwww.bandainamcoid.com%2Fv2%2Foauth2%2Fauth%3Fback%3Dv3%26client_id%3Dnbgi_taiko%26scope%3DJpGroupAll%26redirect_uri%3Dhttps%253A%252F%252Fdonderhiroba.jp%252Flogin_process.php%253Finvite_code%253D%2526abs_back_url%253D%2526location_code%253D%26text%3D&prompt=login";
        wc.getOptions().setThrowExceptionOnScriptError(false);
        try {
            HtmlPage page = (HtmlPage) wc.getPage(url);
            HtmlInput id = (HtmlInput) page.getElementByName("mail");
            HtmlInput password = (HtmlInput) page.getElementByName("pass");
            id.setValueAttribute(loginvo.getUserId());
            password.setValueAttribute(loginvo.getUserPassowrd());
            System.out.println("입력한 id =>" + id.getValueAttribute() + "입력한 pw =>" + password.getValueAttribute());
            password.getValueAttribute();

            HtmlElement button2 = page.getFirstByXPath("//button[@class='btn _btn-size-50 _btn-gray']");
            button2.removeAttribute("disabled");
            button2.removeAllChildren();
            button2.removeStyleAttribute("disabled");

            button2.click();
            // 필수
            Thread.sleep(4000);

            HtmlPage page2 = wc.getPage("https://donderhiroba.jp/login_select.php");
            System.out.println(page2.asNormalizedText());

            Thread.sleep(3000);
            HtmlAnchor button3 = page2.getAnchorByHref("javascript:void(0)");
            button3.click();
            // 유저 정보 페이지
            HtmlPage page3 = wc.getPage("https://donderhiroba.jp/index.php");
            List<HtmlDivision> myDonImg = page3.getByXPath("//img[@class='customd_mydon']");
            HtmlElement element = page3
                    .getFirstByXPath("//div[@id='mydon_area']/div[2]/div[1]");
            System.out.println("element : " + element.asNormalizedText()); // user name
            // final List<?> divs = page.getByXPath("//div");
            // for (int i = 0; i < divs.size(); i++) {
            // System.out.println(divs.get(i));
            // }
            // List<HtmlDivision> myDongInfo = page3.getByXPath("//div[@id='mydon_area']");
            // for (int i = 0; i < myDongInfo.size(); i++) {
            // System.out.println(myDongInfo.get(i));
            // }
            System.out.println(page3.asNormalizedText());
            resultMap.put("message", page3.asXml());
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("message", "서버와 통신하는데 시간이 오래걸리고있습니다.");

        }
        return resultMap;
    }
}