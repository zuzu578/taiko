package com.taiko.taikoproject.taiko.taikoutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TaikoCrawller {
    public void startCrawling() throws IOException {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        List<String> genreList = new ArrayList<String>(
                Arrays.asList("pops", "kids", "anime", "vocaloid", "game", "variety", "classic", "namco"));

        for (int i = 0; i < genreList.size(); i++) {

            Document doc = Jsoup
                    .connect("https://taiko.namco-ch.net/taiko/songlist/" + genreList.get(i) + ".php#sgnavi")
                    .get();
            Elements rowList = doc.select("table"); // 수록곡을 가져옵니다.
            Elements songGenre = doc.select("h3"); // 가져온 수록곡의 장르를 가져옵니다.

            for (Element row : rowList) {
                Elements cellList = row.select("tr"); // (th)
                // 데이터베이스에 새로운 곡을 저장합니다.
                for (int j = 2; j < cellList.size(); j++) {

                    // paramMap.put("songGenre", songGenre.text());
                    paramMap.put("songList", cellList.get(j).text());

                    // HashMap<String, Object> resultParams = trimUtil.utils(paramMap);
                    // resultParams.put("songGenre", songGenre.text());

                    // db.insert(resultParams);

                }
            }

        }

    }

}
