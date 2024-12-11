package com.backinformal.BackInFormal_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backinformal.BackInFormal_Backend.entity.ItemData;

@Repository
public interface ItemRepositroy extends JpaRepository<ItemData, Long>{

}
