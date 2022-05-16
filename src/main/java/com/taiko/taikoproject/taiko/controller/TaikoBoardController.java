package com.taiko.taikoproject.taiko.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.taiko.taikoproject.entity.TaikoBoardCommentListEntity;
import com.taiko.taikoproject.entity.TaikoBoardEntity;
import com.taiko.taikoproject.repository.TaikoBoardCommentListRepository;
import com.taiko.taikoproject.repository.TaikoBoardListRepository;
import com.taiko.taikoproject.taikoDao.TaikoDao;
import com.taiko.taikoproject.taikoVO.TaikoParamVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaikoBoardController {
    private static String FilePath = "/Users/helloworld/vite_react_tutorial/src/assets";
    // 모종의 이유로 잠시 여기에다 하기로 함
    @Autowired
    private SqlSession sqlSession;
    protected static final String NAMESPACE = "com.taiko.taikoproject.taikoDao.";

    @Autowired
    TaikoBoardListRepository taikoBoard;

    @Autowired
    TaikoBoardCommentListRepository comment;

    Optional<TaikoBoardEntity> entity;

    @GetMapping("/board")
    public List<TaikoBoardEntity> getBoardList() {

        List<TaikoBoardEntity> result = taikoBoard.findAll();

        return result;

    }

    @GetMapping("/boardComment")
    public List<TaikoBoardCommentListEntity> getBoardComment(HttpServletRequest req) {
        String boardNo = req.getParameter("boardNo");

        List<TaikoBoardCommentListEntity> result = comment.findCommentListByBoardNo(Integer.parseInt(boardNo));
        return result;
    }

    @PostMapping("/")
    public void insertBoard(@RequestBody TaikoBoardEntity board) {

    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@ModelAttribute TaikoParamVO param, MultipartFile file) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        try {

            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(FilePath, fileName).toString();

            if (fileName.equals("") || fileName == null) {
                fileName = "";
                filePath = "";
                paramMap.put("fileName", fileName);
                paramMap.put("filePath", filePath);
                paramMap.get("id");
                sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadFile", paramMap);
            } else {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
                paramMap.put("fileName", fileName);
                paramMap.put("filePath", "/src/assets/");
                sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadFile", paramMap);
                System.out.println("test!!!!!!" + paramMap.get("id"));
                paramMap.get("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
