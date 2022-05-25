package com.taiko.taikoproject.taiko.controller;

import java.io.IOException;

import com.taiko.taikoproject.taiko.taikoutils.TaikoHirobaLoginUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonderHirobaLogin {

    @GetMapping("/login")
    public String login() throws Exception {
        TaikoHirobaLoginUtils login = new TaikoHirobaLoginUtils();
        try {
            login.login("dlwnghks6821", "lms3821su12");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
