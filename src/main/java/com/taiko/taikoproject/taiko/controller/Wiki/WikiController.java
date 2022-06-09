package com.taiko.taikoproject.taiko.controller.Wiki;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    private static String FilePath = "/Users/helloworld/taiko_wiki/src/assets/image";

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

}
