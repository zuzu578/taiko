package com.taiko.taikoproject.taiko.taikoutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaikoTrimUtils {
    public HashMap<String, Object> utils(HashMap<String, Object> paramMap) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        paramMap.get("songList").toString().replace("-", "").trim();

        List<String> difficulty = new ArrayList<String>();

        // 문자열 뒤에서 부터 자르기.
        for (int i = paramMap.get("songList").toString().length() - 1; i >= 0; i--) {

            difficulty.add(Character.toString(paramMap.get("songList").toString().charAt(i)));

            if (i == paramMap.get("songList").toString().length() - 11) {

                break;
            }
        }

        StringBuffer sb = new StringBuffer(String.join("", difficulty));
        String difficult = sb.reverse().toString();

        // System.out.println(difficult);

        String match = "[^0-9]";
        // 난이도 (숫자) 를 제외한 모든 문자열 제거
        difficult = difficult.replaceAll(match, " ").trim();

        // 불필요한 문자열 제거
        resultMap.put("songs", paramMap.get("songList").toString().replace(difficult, "").replace("NEW!",
                "").replace("-", "")
                .replace("SECRET!", "").replace("〇", "").trim());

        resultMap.put("difficulty", difficult);

        return resultMap;

    }
}
