package com.taiko.taikoproject.taiko.controller.TaikoBoard;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.taiko.taikoproject.entity.TaikoBoardCommentListEntity;
import com.taiko.taikoproject.entity.TaikoBoardEntity;
import com.taiko.taikoproject.entity.TaikoSongListEntity;
import com.taiko.taikoproject.entity.TaikoTjaFileEntity;
import com.taiko.taikoproject.repository.TaikoBoardCommentListRepository;
import com.taiko.taikoproject.repository.TaikoBoardListRepository;
import com.taiko.taikoproject.repository.TaikoCRUDRepository;
import com.taiko.taikoproject.repository.TaikoTjaFileRepository;
import com.taiko.taikoproject.taiko.controller.PasswordCrypto;
import com.taiko.taikoproject.taikoVO.DeleteParam;
import com.taiko.taikoproject.taikoVO.TaikoBoardCommentsVO;
import com.taiko.taikoproject.taikoVO.TaikoParamVO;
import com.taiko.taikoproject.taikoVO.UploadTjaVO;
import com.taiko.taikoproject.taikoVO.WriteCommentParam;

import org.apache.catalina.connector.Response;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    TaikoTjaFileRepository tja;

    @Autowired
    TaikoBoardCommentListRepository comment;

    @Autowired
    TaikoCRUDRepository crud;

    Optional<TaikoBoardEntity> entity;

    @GetMapping("/board")
    public ResponseEntity getBoardList(HttpServletRequest req, final Pageable pageable) {

        String pageNum = req.getParameter("pageNum");
        if (pageNum == "" || pageNum == null) {
            pageNum = "0";
        }

        Pageable result = PageRequest.of(Integer.parseInt(pageNum), 5, Sort.by("createdTime").descending());
        return new ResponseEntity<>(taikoBoard.findBydeletedTimeNull(result), HttpStatus.OK);

    }

    @GetMapping("/boardDetail")
    public ResponseEntity getBoardDetail(HttpServletRequest req) {
        String boardNo = req.getParameter("boardNo");
        return new ResponseEntity<>(taikoBoard.findByboardNo(Integer.parseInt(boardNo)), HttpStatus.OK);
    }

    @GetMapping("/boardComment")
    public ResponseEntity getBoardComment(HttpServletRequest req, final Pageable pageable) {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        String pageNum = req.getParameter("pageNum");
        String boardNo = req.getParameter("boardNo");
        if (pageNum == "" || pageNum == null) {
            pageNum = "0";
        }

        paramMap.put("boardNo", boardNo);
        paramMap.put("pageNum", pageNum);

        List<TaikoBoardCommentsVO> comments = sqlSession
                .selectList("com.taiko.taikoproject.taikoDao." + "selectComments", paramMap);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/postBoard")
    public ResponseEntity<?> uploadFile(@ModelAttribute TaikoParamVO param, MultipartFile file)
            throws NoSuchAlgorithmException {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        PasswordCrypto cipher = new PasswordCrypto();

        if (file == null || file.isEmpty()) {

            String cipherPassowrd = cipher.passwordCrypting(param.getPassword());
            System.out.println("cipherPassword ========>" + cipherPassowrd);
            param.setPassword(cipherPassowrd);
            param.setFileNo(null);
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
                param.setFileNo(paramMap.get("id").toString());

                String cipherPassowrd = cipher.passwordCrypting(param.getPassword());
                param.setPassword(cipherPassowrd);
                sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadPost", param);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public String checkUser(@RequestBody DeleteParam param) throws NoSuchAlgorithmException {
        String result = "";
        String password = param.getPassword();
        int boardNo = Integer.parseInt(param.getBoardNo());
        PasswordCrypto cipher = new PasswordCrypto();
        password = cipher.passwordCrypting(password);
        System.out.println("password =========>" + password);
        entity = taikoBoard.findByboardNoAndPassword(boardNo, password);

        try {
            if (entity.isPresent()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format_time2 = format.format(System.currentTimeMillis());

                entity.ifPresent(item -> {
                    item.setDeletedTime(format_time2);
                    taikoBoard.save(item);
                });
                result = "success";
                return result;

            } else {
                result = "fail";
                return result;
            }
        } catch (Exception error) {
            result = "fail";
            error.printStackTrace();
            return result;
        }

    }

    @GetMapping("/selectTja")
    public ResponseEntity<?> selectTja(HttpServletRequest req) {

        String pageNum = req.getParameter("pageNum");
        if (pageNum == "" || pageNum == null) {
            pageNum = "0";
        }

        Pageable result = PageRequest.of(Integer.parseInt(pageNum), 10, Sort.by("createdTime").descending());
        return new ResponseEntity<>(tja.findAll(result), HttpStatus.OK);

    }

    @PostMapping("/uploadTja")
    public String uploadTja(@ModelAttribute TaikoTjaFileEntity param, MultipartFile file)
            throws NoSuchAlgorithmException {
        String result = "";
        PasswordCrypto cipher = new PasswordCrypto();

        if (file == null || file.isEmpty()) {

            String cipherPassowrd = cipher.passwordCrypting(param.getPassword());
            System.out.println("cipherPassword ========>" + cipherPassowrd);
            param.setPassword(cipherPassowrd);
            tja.save(param);
            result = "success";
            return result;

        }
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String filePath = Paths.get(FilePath, fileName).toString();

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                String cipherPassowrd = cipher.passwordCrypting(param.getPassword());
                stream.write(file.getBytes());
                stream.close();

                param.setFileName(fileName);
                param.setFilePath("/src/assets/");
                param.setPassword(cipherPassowrd);

                tja.save(param);
                result = "success";
                return result;

            }

        } catch (Exception e) {
            result = "fail";
        }

        return result;
    }

    @PostMapping("/comments")
    public ResponseEntity<?> writeComments(@ModelAttribute WriteCommentParam param, MultipartFile file)
            throws NoSuchAlgorithmException {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        PasswordCrypto cipher = new PasswordCrypto();

        if (file == null || file.isEmpty()) {

            String cipherPassowrd = cipher.passwordCrypting(param.getPassword());
            System.out.println("boardNo=======================>" + param.getBoardNo());
            param.setPassword(cipherPassowrd);
            param.setFileNo(null);

            sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadComments", param);
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
                param.setFileNo(paramMap.get("id").toString());

                String cipherPassowrd = cipher.passwordCrypting(param.getPassword());
                param.setPassword(cipherPassowrd);
                sqlSession.insert("com.taiko.taikoproject.taikoDao." + "uploadComments", param);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
