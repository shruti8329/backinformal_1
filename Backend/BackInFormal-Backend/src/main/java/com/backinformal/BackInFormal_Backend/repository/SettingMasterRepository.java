package com.backinformal.BackInFormal_Backend.repository;

import com.backinformal.BackInFormal_Backend.entity.SettingMaster;
import com.backinformal.BackInFormal_Backend.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SettingMasterRepository extends JpaRepository<SettingMaster ,Long> {

    Optional<SettingMaster> findByCompanyName(String companyName);


//    @Query("SELECT s FROM SettingMaster s ORDER BY s.companyName ASC")
//    List<SettingMaster> findSetting(Pageable pageable);

    @Query("SELECT s FROM SettingMaster s ORDER BY s.companyName ASC")
    List<SettingMaster> findAllOrdered();



}
