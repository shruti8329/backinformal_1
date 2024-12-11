package com.backinformal.BackInFormal_Backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SettingMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;
    private String companyName;
    private String companyAddress;
    private  String companyMobile;
    private  String companyEmail;
    private String LogoImage;
    private String GSTIN;
    private String panNumber;
//    @OneToMany(mappedBy = "settingMaster" , cascade = CascadeType.ALL)
//    private List<BankDetails> bankDetailsList;



    public SettingMaster(){
        super();
    }
    public SettingMaster(Long settingId, String companyName, String companyAddress, String companyMobile, String companyEmail, String logoImage, String GSTIN, String panNumber) {
        this.settingId = settingId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyMobile = companyMobile;
        this.companyEmail = companyEmail;
        LogoImage = logoImage;
        this.GSTIN = GSTIN;
        this.panNumber = panNumber;
    }

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getLogoImage() {
        return LogoImage;
    }

    public void setLogoImage(String logoImage) {
        LogoImage = logoImage;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public String getPanNumber() {
        return panNumber;
    }

//    public List<BankDetails> getBankDetailsList() {
//        return bankDetailsList;
//    }
//
//    public void setBankDetailsList(List<BankDetails> bankDetailsList) {
//        this.bankDetailsList = bankDetailsList;
//    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }


    @Override
    public String toString() {
        return "SettingMaster{" +
                "settingId=" + settingId +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyMobile='" + companyMobile + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", LogoImage='" + LogoImage + '\'' +
                ", GSTIN='" + GSTIN + '\'' +
                ", panNumber='" + panNumber + '\'' +
                '}';
    }


}
