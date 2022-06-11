package com.taiko.taikoproject.taiko.controller.Wiki;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.taiko.taikoproject.entity.WikiEntity;
import com.taiko.taikoproject.repository.WikiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/wiki")
@CrossOrigin(origins = "http://localhost:3000")
public class WikiController {

    @Autowired
    WikiRepository wikiRepository;

    private static String FilePath = "/Users/helloworld/taiko_wiki/src/assets/image";

    /**
     * 위키백과에서 게시글에 이미지를 업로드할경우 이미지를 업로드하고 , callback 으로 이미지 경로를 return한다.
     * 
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public HashMap<String, Object> uploadFile(MultipartFile file) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String filePath = Paths.get(FilePath, fileName).toString();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
                resultMap.put("fileName", fileName);
                resultMap.put("filePath", filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @PostMapping("/postingWiki")
    public ResponseEntity postingWiki(@ModelAttribute WikiEntity wikiParam) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_time2 = format.format(System.currentTimeMillis());
        wikiParam.setCreated_time(format_time2);

        wikiRepository.save(wikiParam);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/getWikiData")
    public ResponseEntity getWikiData(HttpServletRequest req) {
        String title = req.getParameter("searchKeyword").toString();
        WikiEntity result = wikiRepository.findBytitleContaining(title);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
