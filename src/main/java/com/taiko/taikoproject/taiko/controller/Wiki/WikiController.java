package com.taiko.taikoproject.taiko.controller.Wiki;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class WikiController {

    private static String FilePath = "/Users/helloworld/taiko_wiki/src/assets/image";

    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file) {

        try {
            if (!file.isEmpty()) {

                String fileName = file.getOriginalFilename();
                String filePath = Paths.get(FilePath, fileName).toString();

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
