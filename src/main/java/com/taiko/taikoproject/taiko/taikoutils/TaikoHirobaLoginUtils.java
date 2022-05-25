package com.taiko.taikoproject.taiko.taikoutils;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TaikoHirobaLoginUtils {

    private String id = "dlwnghks6821@naver.com";
    private String password = "lms3821su";

    public String hirobaLogin() throws IOException {
        Jsoup.connect("https://donderhiroba.jp/login.php").get();
        Connection.Response loginPageResponse = Jsoup.connect("https://donderhiroba.jp/login.php")
                .timeout(3000)
                .header("Origin", "http://test.co.kr")
                .header("Referer", "https://donderhiroba.jp/login.php")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4")
                .method(Connection.Method.GET)
                .execute();

        Map<String, String> cookies = loginPageResponse.cookies();
        Connection response = (Connection) Jsoup.connect("https://donderhiroba.jp/login.php")
                .data("member_id", id, "member_password", password)
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
                .cookie("PHPSESSID", loginPageResponse.cookies().get("PHPSESSID"))
                .method(Connection.Method.POST)
                .timeout(5000)
                .execute();

        // Document doc =
        // Jsoup.connect("http://www.test.co.kr/product/product_detail.htm?ProductNo=test")
        // .cookies(response.cookies()).get();

        return null;
    }
}
