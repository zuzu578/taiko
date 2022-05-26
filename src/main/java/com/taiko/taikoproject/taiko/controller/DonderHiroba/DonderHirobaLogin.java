package com.taiko.taikoproject.taiko.controller.DonderHiroba;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import com.shapesecurity.salvation2.Values.Hash;
import com.taiko.taikoproject.entity.DonderHirobaEntity;
import com.taiko.taikoproject.repository.DonderHirobaRepository;
import com.taiko.taikoproject.taiko.taikoutils.TaikoHirobaLoginUtils;
import com.taiko.taikoproject.taikoVO.DonderHirobaLoginParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DonderHirobaLogin {

    @Autowired
    DonderHirobaRepository donderHirobaRepository;

    Optional<DonderHirobaEntity> donderHirobaEntity;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DonderHirobaLoginParam loginvo) throws Exception {
        TaikoHirobaLoginUtils login = new TaikoHirobaLoginUtils();
        DonderHirobaEntity donderHiroba = new DonderHirobaEntity();
        HashMap<String, Object> result = new HashMap<String, Object>();
        String status = "";
        try {
            result = login.login(loginvo, donderHiroba);
            donderHirobaEntity = donderHirobaRepository.findByuserMailAndUserPassword(loginvo.getUserId(),
                    loginvo.getUserPassowrd());
            if (!result.get("message").equals("fail")) {
                // 유저 데이터가 존재할 경우 원본으로부터 업데이트
                if (donderHirobaEntity.isPresent()) {
                    status = "update";
                    donderHirobaEntity.ifPresent(item -> {
                        item.setUserBestRank2(donderHiroba.getUserBestRank2());
                        item.setUserBestRank2(donderHiroba.getUserBestRank3());
                        item.setUserBestRank2(donderHiroba.getUserBestRank4());
                        item.setUserBestRank2(donderHiroba.getUserBestRank5());
                        item.setUserBestRank2(donderHiroba.getUserBestRank6());
                        item.setUserBestRank2(donderHiroba.getUserBestRank7());
                        item.setUserBestRank2(donderHiroba.getUserBestRank8());
                        item.setUserDanwi(donderHiroba.getUserDanwi());
                        item.setUserDonderfulCrown(donderHiroba.getUserDonderfulCrown());
                        item.setUserDonmedal(donderHiroba.getUserDonmedal());
                        item.setUserGoldCrown(donderHiroba.getUserGoldCrown());
                        item.setUserMydon(donderHiroba.getUserMydon());
                        item.setUserName(donderHiroba.getUserName());
                        item.setUserSilverCrown(donderHiroba.getUserSilverCrown());
                        item.setUserStyle(donderHiroba.getUserStyle());
                        item.setUserToken(donderHiroba.getUserToken());
                        donderHirobaRepository.save(item);

                    });
                } else {
                    status = "sync";
                    // 존재하지 않을경우 연동
                    donderHirobaRepository.save(donderHiroba);
                }
                if (status.equals("update")) {
                    return new ResponseEntity<>("기존데이터를 새로 업데이트하는데 성공햇습니다.🎊", HttpStatus.OK);
                } else if (status.equals("sync")) {
                    return new ResponseEntity<>("연동에 성공하였습니다.🎉", HttpStatus.OK);
                }
            } else {

                result.put("message", "아이디 혹은 비밀번호를 다시확인해주세요.😅");

            }
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);

        } catch (IOException e) {
            e.printStackTrace();
            result.put("message", "아이디 혹은 비밀번호를 다시확인해주세요.");
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }

    }

}
