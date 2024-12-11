package com.backinformal.BackInFormal_Backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backinformal.BackInFormal_Backend.service.CloudinaryService;

@RestController
@RequestMapping("/cloudinary/upload")
@CrossOrigin
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryImageService;

    @PostMapping
    ResponseEntity<Map> uploadImage(@RequestParam("image") MultipartFile file) {

        Map data = cloudinaryImageService.upload(file);

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

}
