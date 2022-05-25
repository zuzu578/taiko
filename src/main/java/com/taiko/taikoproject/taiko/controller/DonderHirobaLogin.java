package com.taiko.taikoproject.taiko.controller;

import java.io.IOException;

import com.taiko.taikoproject.taiko.taikoutils.TaikoHirobaLoginUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonderHirobaLogin {

    @GetMapping("/login")
    public String login() {
        TaikoHirobaLoginUtils login = new TaikoHirobaLoginUtils();
        try {
            login.hirobaLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
