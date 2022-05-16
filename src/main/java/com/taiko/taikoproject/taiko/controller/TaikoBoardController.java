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

import com.taiko.taikoproject.taikoVO.TaikoParamVO;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public ResponseEntity getBoardList(final Pageable pageable) {

        // Page<TaikoBoardEntity> result =
        // taikoBoard.findAll(Sort.by(Sort.Direction.DESC, "createdTime"));
        Page<TaikoBoardEntity> result = taikoBoard.findAll(pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/boardComment")
    public ResponseEntity getBoardComment(HttpServletRequest req, final Pageable pageable) {
        String boardNo = req.getParameter("boardNo");

        Page<TaikoBoardCommentListEntity> result = (Page<TaikoBoardCommentListEntity>) comment.findCommentListByBoardNo(
                Integer.parseInt(boardNo),
                pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/postBoard")
    public ResponseEntity<?> uploadFile(@ModelAttribute TaikoParamVO param, MultipartFile file) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        if (file == null || file.isEmpty()) {

            paramMap.put("fileName", "");
            paramMap.put("filePath", "");
            paramMap.get("id");

            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadFile", paramMap);
            // param.setFileNo('"');
            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadPost", param);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        try {

            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String filePath = Paths.get(FilePath, fileName).toString();

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();

                paramMap.put("fileName", fileName);
                paramMap.put("filePath", "/src/assets/");

                sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadFile", paramMap);
                param.setFileNo(Integer.parseInt(paramMap.get("id").toString()));
                sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadPost", param);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
