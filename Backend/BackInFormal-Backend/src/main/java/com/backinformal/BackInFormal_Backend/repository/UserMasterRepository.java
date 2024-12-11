package com.backinformal.BackInFormal_Backend.repository;

import com.backinformal.BackInFormal_Backend.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMasterRepository extends JpaRepository<UserMaster , Integer> {


    Optional<UserMaster> findByUserName(String userName);

	Optional<UserMaster> findByEmail(String email);
}
