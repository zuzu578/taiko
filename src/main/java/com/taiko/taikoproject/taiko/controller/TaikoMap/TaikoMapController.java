package com.taiko.taikoproject.taiko.controller.TaikoMap;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.taiko.taikoproject.taiko.taikoutils.TaikoMapUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaikoMapController {

    @GetMapping("/map")
    public String test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        TaikoMapUtils taiko = new TaikoMapUtils();
        taiko.getTaikoMap();
        return null;

    }

}
