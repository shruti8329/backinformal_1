package com.backinformal.BackInFormal_Backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backinformal.BackInFormal_Backend.entity.SettingMaster;
import com.backinformal.BackInFormal_Backend.repository.SettingMasterRepository;

import jakarta.annotation.PostConstruct;


@Service
public class SettingMasterService {

    @Autowired
    private SettingMasterRepository settingMasterRepository;

    @Value("${project.image}")
    private String path;
    
    @PostConstruct
	public void myInit() {
		System.out.println("in Business Setting  " + path);
		// chk of folder exists --o.w create one!
		File folderName = new File(path);
		if (!folderName.exists()) {
			folderName.mkdirs();
		} else
			System.out.println("Logo image folder alrdy exists....");
	}
    
    
    @Autowired
    private CloudinaryService cloudinaryService;


    public List<SettingMaster> getAllSettings() {
        //how to get image
        return settingMasterRepository.findAll();
    }


    public SettingMaster createSetting(SettingMaster settingMaster, MultipartFile file) throws IOException {

//        Map<String, Object> logo = cloudinaryService.upload(file);
//        String url = (String) logo.get("url");
//        settingMaster.setLogoImage(url);
        //create File if not Created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        //File Name
        String fileName = file.getOriginalFilename();
        System.out.println("filename " + fileName);

        //random generate for file
        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat((fileName.substring(fileName.lastIndexOf("."))));

        //Full path
        String filePath = path + File.separator + fileName1;
        System.out.println("filePath " + filePath);


        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        settingMaster.setLogoImage(fileName);

        return settingMasterRepository.save(settingMaster);
    }


//    public SettingMaster updateSetting(SettingMaster settingMaster, MultipartFile logoImage) throws IOException {
//
//
//        System.out.println("IMAGE UPDATED--settingName - " + settingMaster.getCompanyName());
//
//        SettingMaster existingSetting = settingMasterRepository.findSetting((Pageable) PageRequest.of(0, 1)).orElse(null);
//
//
//        System.out.println("IMAGE UPDATED--existingSetting " + existingSetting);
//
//        if (existingSetting != null) {
//
//            System.out.println("IMAGE UPDATED--1");
//
//            //existingSetting.setSettingId(settingId);
//            existingSetting.setCompanyName(settingMaster.getCompanyName());
//            existingSetting.setCompanyAddress(settingMaster.getCompanyAddress());
//            existingSetting.setCompanyEmail(settingMaster.getCompanyEmail());
//            existingSetting.setCompanyMobile(settingMaster.getCompanyMobile());
//            existingSetting.setGSTIN(settingMaster.getPanNumber());
//            existingSetting.setPanNumber(settingMaster.getPanNumber());
//
//            // Check if a new image file is provided
//            if (logoImage != null && !logoImage.isEmpty()) {
//                // Delete the old image file if it exists
//                System.out.println("IMAGE UPDATED--2");
//
//                String oldFilePath = path + File.separator + existingSetting.getLogoImage();
//                File oldFile = new File(oldFilePath);
//                if (oldFile.exists()) {
//                    oldFile.delete();
//                }
//
//                System.out.println("IMAGE UPDATED--3");
//                // Save the new image
//                String fileName = logoImage.getOriginalFilename();
//                String randomId = UUID.randomUUID().toString();
//                String newFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
//                String newFilePath = path + File.separator + newFileName;
//
//                Files.copy(logoImage.getInputStream(), Paths.get(newFilePath));
//                existingSetting.setLogoImage(newFileName);
////            if(file != null && !file.isEmpty()){
////                Map<String, Object> logo = cloudinaryService.upload(file);
////                String url = (String) logo.get("url");
////                existingSetting.setLogoImage(url);
////                settingMasterRepository.save(existingSetting);
////            }
//
//                // Create the directory if it doesn't exist
//                System.out.println("IMAGE UPDATED--2");
//
//
//
//            }
//
//           // return settingMasterRepository.save(existingSetting);
//        }
//
//        return settingMasterRepository.save(existingSetting);
//    }
public SettingMaster updateSetting(SettingMaster settingMaster, MultipartFile logoImage) throws IOException {
    try {
        System.out.println("IMAGE UPDATED--settingName - " + settingMaster.getCompanyName());

        List<SettingMaster> settings = settingMasterRepository.findAllOrdered();
        SettingMaster existingSetting = settings.isEmpty() ? null : settings.get(0);

        if (existingSetting != null) {
            // Update existing setting
            existingSetting.setCompanyName(settingMaster.getCompanyName());
            existingSetting.setCompanyAddress(settingMaster.getCompanyAddress());
            existingSetting.setCompanyEmail(settingMaster.getCompanyEmail());
            existingSetting.setCompanyMobile(settingMaster.getCompanyMobile());
            existingSetting.setGSTIN(settingMaster.getGSTIN());
            existingSetting.setPanNumber(settingMaster.getPanNumber());

            if (logoImage != null && !logoImage.isEmpty()) {
                String oldFilePath = path + File.separator + existingSetting.getLogoImage();
                File oldFile = new File(oldFilePath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }

                String fileName = logoImage.getOriginalFilename();
                String randomId = UUID.randomUUID().toString();
                String newFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
                String newFilePath = path + File.separator + newFileName;

                Files.copy(logoImage.getInputStream(), Paths.get(newFilePath));
                existingSetting.setLogoImage(newFileName);
            }

            return settingMasterRepository.save(existingSetting);
        } else {
            // Save new setting
            if (logoImage != null && !logoImage.isEmpty()) {
                String fileName = logoImage.getOriginalFilename();
                String randomId = UUID.randomUUID().toString();
                String newFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
                String newFilePath = path + File.separator + newFileName;

                Files.copy(logoImage.getInputStream(), Paths.get(newFilePath));
                settingMaster.setLogoImage(newFileName);
            }

            return settingMasterRepository.save(settingMaster);
        }
    } catch (IOException e) {
        System.err.println("File handling error: " + e.getMessage());
        throw e;
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Failed to update setting", e);
    }
}

    public ResponseEntity<?> getSettingById(Long settingId) {
        Optional<SettingMaster> bankDetailsOpt = settingMasterRepository.findById(settingId);

        if (bankDetailsOpt.isPresent()) {
            return ResponseEntity.ok(bankDetailsOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("BankDetails with ID " + settingId + " not found.");
        }
    }


}
