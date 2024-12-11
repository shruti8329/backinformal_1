package com.backinformal.BackInFormal_Backend.controller;

import com.backinformal.BackInFormal_Backend.entity.BankDetails;
import com.backinformal.BackInFormal_Backend.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankDetails")
public class BankDetailsController {
    @Autowired
    private BankDetailsService bankDetailsService;


    @PostMapping("/new-bank/{settingId}")
    public ResponseEntity<?> addNewBank(@RequestBody BankDetails bankDetails,
                                        @PathVariable Long settingId) {
        return bankDetailsService.addNewBank(bankDetails, settingId);

    }

    @GetMapping("/getAllBanks")
    public List<BankDetails> getAllBankDetails() {
        return bankDetailsService.getAllBankDetails();

    }

    @GetMapping("/{bankId}")
    public ResponseEntity<?> addNewBank(@PathVariable Long bankId) {
        return bankDetailsService.getBankById(bankId);

    }

    @PutMapping("/update-bank/{settingId}")
    public ResponseEntity<?> updateBankDetails(@RequestBody BankDetails bankDetails,
                                               @PathVariable Long settingId ) {
        return bankDetailsService.updateBank(bankDetails,settingId);

    }
}
