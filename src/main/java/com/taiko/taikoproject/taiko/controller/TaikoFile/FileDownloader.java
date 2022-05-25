package com.taiko.taikoproject.taiko.controller.TaikoFile;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.taikoproject.taiko.taikoutils.FileDownload;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloader {
    final static String FILE_SERVER_PATH = "/Users/helloworld/vite_react_tutorial";

    @GetMapping("/fileDownload")
    public void songDownLoad(HttpServletRequest req, HttpServletResponse res) {
        String fileName = req.getParameter("fileName");
        String filePath = req.getParameter("filePath");
        FileDownload file = new FileDownload();
        try {
            file.filDown(req, res, FILE_SERVER_PATH + filePath, fileName, fileName);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
