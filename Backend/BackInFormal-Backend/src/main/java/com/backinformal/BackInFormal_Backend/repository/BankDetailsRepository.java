package com.backinformal.BackInFormal_Backend.repository;

import com.backinformal.BackInFormal_Backend.entity.BankDetails;
import com.backinformal.BackInFormal_Backend.entity.SettingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankDetailsRepository extends JpaRepository<BankDetails,Long> {

   Optional<BankDetails> findByAccountNumber(String accountNumber);

   @Query("SELECT b FROM BankDetails b ORDER BY b.bankName ASC")
   List<BankDetails> findAllBank();

}
