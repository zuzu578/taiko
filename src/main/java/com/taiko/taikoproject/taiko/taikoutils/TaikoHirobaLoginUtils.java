package com.taiko.taikoproject.taiko.taikoutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import com.taiko.taikoproject.entity.DonderHirobaEntity;
import com.taiko.taikoproject.entity.UserFavoriteSongEntity;
import com.taiko.taikoproject.entity.UserLikeSongEntity;
import com.taiko.taikoproject.taikoVO.DonderHirobaLoginParam;

public class TaikoHirobaLoginUtils {

    public HashMap<String, Object> login(DonderHirobaLoginParam loginvo, DonderHirobaEntity donderHiroba,
            UserFavoriteSongEntity favoriteSongEntity, UserLikeSongEntity userLikeSongEntity)
            throws Exception {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        WebClient wc = new WebClient();

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
            Thread.sleep(5000);

            HtmlPage page2 = wc.getPage("https://donderhiroba.jp/login_select.php");
            System.out.println("----------------------동더 히로바 로그인 중 ... ------------------------");

            Thread.sleep(3500);

            try {
                HtmlAnchor button3 = page2.getAnchorByHref("javascript:void(0)");
                button3.click();
            } catch (Exception e) {
                resultMap.put("message", "fail");
                return resultMap;
            }

            // 유저 정보 페이지
            HtmlPage page3 = wc.getPage("https://donderhiroba.jp/index.php");
            HtmlElement myDonImg = page3.getFirstByXPath("//div[@id='mydon_area']/div[3]/div[2]/img"); // 마이동 이미지

            System.out.println("마이동 이미지 : " + myDonImg);
            donderHiroba.setUserMydon(myDonImg.toString());

            HtmlElement userName = page3
                    .getFirstByXPath("//div[@id='mydon_area']/div[2]/div[1]"); // 유저이름
            System.out.println("유저이름 : " + userName.asNormalizedText());
            donderHiroba.setUserName(userName.asNormalizedText());

            HtmlElement style = page3.getFirstByXPath("//div[@id='mydon_area']/div[1]"); // 칭호
            System.out.println("스타일 : " + style.asNormalizedText());
            donderHiroba.setUserStyle(style.asNormalizedText());
            HtmlElement danwi = page3.getFirstByXPath("//div[@id='mydon_area']/div[2]/div[2]/img"); // 칭호
            System.out.println("단위 : " + danwi);
            donderHiroba.setUserDanwi(danwi.toString());

            List<String> userScore = new ArrayList<String>();
            for (int i = 1; i <= 10; i++) {
                HtmlElement score = page3.getFirstByXPath("//div[@id='mydon_area']/div[4]/div[" + i + "]");
                System.out.println("점수 : " + score.asNormalizedText());
                userScore.add(score.asNormalizedText());
            }
            donderHiroba.setUserBestRank8(userScore.get(0));
            donderHiroba.setUserBestRank7(userScore.get(1));
            donderHiroba.setUserBestRank6(userScore.get(2));
            donderHiroba.setUserBestRank5(userScore.get(3));
            donderHiroba.setUserBestRank4(userScore.get(4));
            donderHiroba.setUserBestRank3(userScore.get(5));
            donderHiroba.setUserBestRank2(userScore.get(6));
            donderHiroba.setUserSilverCrown(userScore.get(7));
            donderHiroba.setUserGoldCrown(userScore.get(8));
            donderHiroba.setUserDonderfulCrown(userScore.get(9));

            HtmlElement donMedal = page3.getFirstByXPath("//div[@id='mydon_area']/div[5]/div[1]/div[2]");
            HtmlElement token = page3.getFirstByXPath("//div[@id='mydon_area']/div[5]/div[2]/div[2]");
            System.out.println("동메달 : " + donMedal.asNormalizedText() + "토큰 : " + token.asNormalizedText());

            donderHiroba.setUserDonmedal(donMedal.asNormalizedText());
            donderHiroba.setUserToken(token.asNormalizedText());

            donderHiroba.setUserMail(loginvo.getUserId());
            donderHiroba.setUserPassword(loginvo.getUserPassowrd());

            HtmlElement button3 = page3.getFirstByXPath("//img[@class='buttonImage']");
            button3.click();
            Thread.sleep(3000);
            // 좋아하는 곡 list page
            HtmlPage page4 = wc.getPage("https://donderhiroba.jp/mypage_top.php");

            DomNodeList<DomElement> optionStatusList = page4.getElementsByTagName("li");
            List<String> favoriteSongList = new ArrayList<String>();

            for (int i = 0; i < optionStatusList.size(); i++) {
                System.out.println("test" + optionStatusList.get(i).asNormalizedText());
                favoriteSongList.add(optionStatusList.get(i).asNormalizedText());
            }
            // 옷갈아 입히기 page
            HtmlPage page5 = wc.getPage("https://donderhiroba.jp/mypage_kisekae.php");

            HtmlElement button4 = page5.getFirstByXPath("/html/body/div/div/div[1]/div[2]/div[1]/ul[2]/li[1]");
            button4.click();

            List<?> atamaList = page5.getByXPath("/html/body/div/div/div[1]/div[2]/div[2]/div[5]/div[2]/ul/li/a/img");

            for (int i = 0; i < atamaList.size(); i++) {
                System.out.println(atamaList.get(i));
            }
            HtmlElement button5 = page5.getFirstByXPath("/html/body/div/div/div[1]/div[2]/div[1]/ul[2]/li[3]");
            button5.click();

            List<?> kigurumiList = page5
                    .getByXPath("/html/body/div/div/div[1]/div[2]/div[2]/div[4]/div[2]/ul/li/a/img");

            HtmlElement button6 = page5.getFirstByXPath("/html/body/div/div/div[1]/div[2]/div[1]/ul[2]/li[5]");
            button6.click();

            List<?> puchiCharacter = page5
                    .getByXPath("/html/body/div/div/div[1]/div[2]/div[2]/div[8]/div[2]/ul/li/a/img");

            HtmlElement button7 = page5.getFirstByXPath("/html/body/div/div/div[1]/div[2]/div[1]/ul[2]/li[2]");
            button7.click();

            List<?> karadaList = page5
                    .getByXPath("/html/body/div/div/div[1]/div[2]/div[2]/div[6]/div[2]/ul/li/a/img");

            HtmlElement button8 = page5.getFirstByXPath("/html/body/div/div/div[1]/div[2]/div[1]/ul[2]/li[4]");
            button8.click();

            List<?> makeUpList = page5
                    .getByXPath("/html/body/div/div/div[1]/div[2]/div[2]/div[7]/div[2]/ul/li/a/img");

            resultMap.put("message", "데이터연동이 정상적으로 처리되었습니다.");
            resultMap.put("favorites", favoriteSongList);
            resultMap.put("atamaList", atamaList);
            resultMap.put("kigurumiList", kigurumiList);
            resultMap.put("puchiCharacter", puchiCharacter);
            resultMap.put("karadaList", karadaList);
            resultMap.put("makeUpList", makeUpList);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("message", "fail");

        }
        return resultMap;
    }
}