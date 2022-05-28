package com.taiko.taikoproject.repository;

import java.util.List;

import com.taiko.taikoproject.entity.DonderHirobaUserCostumeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonderHirobaUserCostumeRepository extends JpaRepository<DonderHirobaUserCostumeEntity, Integer> {

    List<DonderHirobaUserCostumeEntity> findByuserIdx(int parseInt);

}
