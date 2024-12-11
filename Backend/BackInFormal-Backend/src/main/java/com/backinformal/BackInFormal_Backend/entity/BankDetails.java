package com.backinformal.BackInFormal_Backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankDetailId;
    private  String bankName;
    private String accountHolderName;
    private  String accountNumber;
    private String branchName;
    private  String  ifscCode;
    private  String accountType;
    private  String branchAddress;

   // private  String bankPhoneNumber;

   // private String emailAddress;
   // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "settingId")
    private  SettingMaster settingMaster;

    public BankDetails(){
        super();
    }

    public BankDetails(Long bankDetailId, String bankName, String accountHolderName, String accountNumber, String branchName, String ifscCode, String accountType, String branchAddress, SettingMaster settingMaster) {
        this.bankDetailId = bankDetailId;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
        this.settingMaster = settingMaster;
    }

    public Long getBankDetailId() {
        return bankDetailId;
    }

    public void setBankDetailId(Long bankDetailId) {
        this.bankDetailId = bankDetailId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public SettingMaster getSettingMaster() {
        return settingMaster;
    }

    public void setSettingMaster(SettingMaster settingMaster) {
        this.settingMaster = settingMaster;
    }

    @Override
    public String toString() {
        return "BankDetails{" +
                "bankDetailId=" + bankDetailId +
                ", bankName='" + bankName + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", branchName='" + branchName + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", accountType='" + accountType + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", settingMaster=" + settingMaster +
                '}';
    }
}
