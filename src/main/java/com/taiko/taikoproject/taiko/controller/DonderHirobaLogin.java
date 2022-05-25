package com.taiko.taikoproject.taiko.controller;

import java.io.IOException;
import java.util.HashMap;

import com.shapesecurity.salvation2.Values.Hash;
import com.taiko.taikoproject.taiko.taikoutils.TaikoHirobaLoginUtils;
import com.taiko.taikoproject.taikoVO.DonderHirobaLoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonderHirobaLogin {

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody DonderHirobaLoginParam loginvo) throws Exception {
        TaikoHirobaLoginUtils login = new TaikoHirobaLoginUtils();
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            result = login.login(loginvo);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result.put("message", "아이디 혹은 비밀번호를 다시확인해주세요.");
            return result;
        }

    }

}
