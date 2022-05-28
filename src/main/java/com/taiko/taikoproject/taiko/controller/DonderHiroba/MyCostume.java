package com.taiko.taikoproject.taiko.controller.DonderHiroba;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.taiko.taikoproject.entity.DonderHirobaUserCostumeEntity;
import com.taiko.taikoproject.repository.DonderHirobaUserCostumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MyCostume {
    @Autowired
    DonderHirobaUserCostumeRepository donderHirobaUserCostumeRepository;

    public List<DonderHirobaUserCostumeEntity> getCostume(HttpServletRequest req) {
        String userIdx = req.getParameter("userIdx");
        List<DonderHirobaUserCostumeEntity> result = donderHirobaUserCostumeRepository
                .findByuserId(Integer.parseInt(userIdx));
        return result;
    }
}
