package com.taiko.taikoproject.taiko.controller.DonderHiroba;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.taiko.taikoproject.entity.DonderHirobaEntity;
import com.taiko.taikoproject.entity.UserFavoriteSongEntity;
import com.taiko.taikoproject.repository.DonderHirobaRepository;
import com.taiko.taikoproject.repository.UserFavoriteSongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MyPage {
    @Autowired
    DonderHirobaRepository donderHirobaRepository;
    @Autowired
    UserFavoriteSongRepository userFavoriteSongRepository;

    /**
     * 동더히로바 마이페이지
     * 
     * @param req
     * @return
     */
    @GetMapping("/myPage")
    public List<DonderHirobaEntity> myPage(HttpServletRequest req) {
        String userMail = req.getParameter("userMail");
        List<DonderHirobaEntity> result = donderHirobaRepository.findByuserMail(userMail);
        return result;
    }

    /**
     * 즐겨찾기 곡
     * 
     * @return
     */
    @GetMapping("/myFavoriteSongList")
    public List<UserFavoriteSongEntity> myFavoriteSongList() {
        List<UserFavoriteSongEntity> result = userFavoriteSongRepository.findAll();
        return result;
    }

}
