package com.taiko.taikoproject.taiko.controller.DonderHiroba;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.taiko.taikoproject.entity.DonderHirobaEntity;
import com.taiko.taikoproject.entity.DonderHirobaUserCostumeEntity;
import com.taiko.taikoproject.entity.UserFavoriteSongEntity;
import com.taiko.taikoproject.entity.UserLikeSongEntity;
import com.taiko.taikoproject.repository.DonderHirobaRepository;
import com.taiko.taikoproject.repository.UserFavoriteSongRepository;
import com.taiko.taikoproject.taiko.taikoutils.TaikoHirobaLoginUtils;
import com.taiko.taikoproject.taikoVO.DonderHirobaLoginParam;

import org.apache.ibatis.session.SqlSession;
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

    @Autowired
    UserFavoriteSongRepository userFavoriteSongRepository;

    Optional<DonderHirobaEntity> donderHirobaEntityOptional;
    Optional<UserFavoriteSongEntity> userFavoriteSongEntityOptional;

    @Autowired
    private SqlSession sqlSession;
    protected static final String NAMESPACE = "com.taiko.taikoproject.taikoDao.";

    /**
     * 동더히로바 로그인
     * 
     * @param loginvo
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DonderHirobaLoginParam loginvo) throws Exception {

        TaikoHirobaLoginUtils login = new TaikoHirobaLoginUtils();
        DonderHirobaEntity donderHiroba = new DonderHirobaEntity();
        UserFavoriteSongEntity favoriteSongEntity = new UserFavoriteSongEntity();
        DonderHirobaUserCostumeEntity donderHirobaUserCostumeEntity = new DonderHirobaUserCostumeEntity();
        UserLikeSongEntity userLikeSongEntity = new UserLikeSongEntity();

        HashMap<String, Object> result = new HashMap<String, Object>();
        String status = "";
        try {
            result = login.login(loginvo, donderHiroba, favoriteSongEntity, userLikeSongEntity);
            donderHirobaEntityOptional = donderHirobaRepository.findByuserMailAndUserPassword(loginvo.getUserId(),
                    loginvo.getUserPassowrd());

            int userIdx = donderHirobaEntityOptional.get().getUserNo();
            // 즐겨찾기 설정한 곡 list
            List favoriteList = (List) result.get("favorites");
            // 보유하고있는 user costume
            List atamaList = (List) result.get("atamaList");
            List kigurumiList = (List) result.get("kigurumiList");
            List puchiCharacter = (List) result.get("puchiCharacter");
            List karadaList = (List) result.get("karadaList");
            List makeUpList = (List) result.get("makeUpList");

            if (!result.get("message").equals("fail")) {
                // 유저 데이터가 존재할 경우 원본으로부터 업데이트
                if (donderHirobaEntityOptional.isPresent()) {
                    status = "update";
                    donderHirobaEntityOptional.ifPresent(item -> {
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

                    favoriteSongEntity.setUserIdx(userIdx);
                    donderHirobaUserCostumeEntity.setUserIdx(userIdx);
                    int songCount = sqlSession.selectOne("com.taiko.taikoproject.taikoDao." + "selectSongCountByUser",
                            favoriteSongEntity);
                    int costumeCount = sqlSession.selectOne(
                            "com.taiko.taikoproject.taikoDao." + "selectUserCostume",
                            donderHirobaUserCostumeEntity);

                    HashMap<String, Object> paramMap = null;
                    if (costumeCount == 0) {

                        for (int i = 0; i < atamaList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", atamaList.get(i));
                            paramMap.put("costumeType", "atama");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);
                        }
                        for (int i = 0; i < kigurumiList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", kigurumiList.get(i));
                            paramMap.put("costumeType", "kigurumi");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                        for (int i = 0; i < puchiCharacter.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", puchiCharacter.get(i));
                            paramMap.put("costumeType", "puchiCharacter");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                        for (int i = 0; i < karadaList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", karadaList.get(i));
                            paramMap.put("costumeType", "karada");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                        for (int i = 0; i < makeUpList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", makeUpList.get(i));
                            paramMap.put("costumeType", "makeup");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                    }
                    if (costumeCount > 0) {
                        sqlSession.delete("com.taiko.taikoproject.taikoDao." + "deleteCostume",
                                paramMap);

                        for (int i = 0; i < atamaList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", atamaList.get(i));
                            paramMap.put("costumeType", "atama");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);
                        }
                        for (int i = 0; i < kigurumiList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", kigurumiList.get(i));
                            paramMap.put("costumeType", "kigurumi");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                        for (int i = 0; i < puchiCharacter.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", puchiCharacter.get(i));
                            paramMap.put("costumeType", "puchiCharacter");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                        for (int i = 0; i < karadaList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", karadaList.get(i));
                            paramMap.put("costumeType", "karada");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                        for (int i = 0; i < makeUpList.size(); i++) {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("costume", makeUpList.get(i));
                            paramMap.put("costumeType", "makeup");
                            paramMap.put("userIdx", userIdx);
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                    paramMap);

                        }
                    }
                    if (songCount == 0) {
                        for (int i = 2; i < favoriteList.size(); i++) {
                            favoriteSongEntity.setUserFavoriteSong(favoriteList.get(i).toString());
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertFavoriteSong",
                                    favoriteSongEntity);
                        }

                    }

                    if (songCount > 0) {
                        sqlSession.delete("com.taiko.taikoproject.taikoDao." + "deleteFavoriteSong",
                                favoriteSongEntity);
                        for (int j = 0; j < favoriteList.size(); j++) {
                            favoriteSongEntity.setUserFavoriteSong(favoriteList.get(j).toString());
                            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertFavoriteSong",
                                    favoriteSongEntity);
                        }

                    }

                } else {
                    status = "sync";
                    // 존재하지 않을경우 연동
                    HashMap<String, Object> paramMap = null;
                    donderHirobaRepository.save(donderHiroba);
                    for (int i = 2; i < favoriteList.size(); i++) {
                        favoriteSongEntity.setUserFavoriteSong(favoriteList.get(i).toString());
                        sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertFavoriteSong",
                                favoriteSongEntity);
                    }

                    for (int i = 0; i < atamaList.size(); i++) {
                        paramMap = new HashMap<String, Object>();
                        paramMap.put("costume", atamaList.get(i));
                        paramMap.put("costumeType", "atama");
                        paramMap.put("userIdx", userIdx);
                        sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                paramMap);
                    }
                    for (int i = 0; i < kigurumiList.size(); i++) {
                        paramMap = new HashMap<String, Object>();
                        paramMap.put("costume", kigurumiList.get(i));
                        paramMap.put("costumeType", "kigurumi");
                        paramMap.put("userIdx", userIdx);
                        sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                paramMap);

                    }
                    for (int i = 0; i < puchiCharacter.size(); i++) {
                        paramMap = new HashMap<String, Object>();
                        paramMap.put("costume", puchiCharacter.get(i));
                        paramMap.put("costumeType", "puchiCharacter");
                        paramMap.put("userIdx", userIdx);
                        sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                paramMap);

                    }
                    for (int i = 0; i < karadaList.size(); i++) {
                        paramMap = new HashMap<String, Object>();
                        paramMap.put("costume", karadaList.get(i));
                        paramMap.put("costumeType", "karada");
                        paramMap.put("userIdx", userIdx);
                        sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                paramMap);

                    }
                    for (int i = 0; i < makeUpList.size(); i++) {
                        paramMap = new HashMap<String, Object>();
                        paramMap.put("costume", makeUpList.get(i));
                        paramMap.put("costumeType", "makeup");
                        paramMap.put("userIdx", userIdx);
                        sqlSession.insert("com.taiko.taikoproject.taikoDao." + "insertCostumeList",
                                paramMap);

                    }
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
            result.put("message", "서버에 오류가 있습니다.");
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }

    }

}
