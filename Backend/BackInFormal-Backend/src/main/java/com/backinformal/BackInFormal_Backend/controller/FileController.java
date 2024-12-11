package com.backinformal.BackInFormal_Backend.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*")
public class FileController {

	// For GET Image of Bussiness Logo from business_logo folder
    private final Path businessLogoFolder = Paths.get("images");
    
    @GetMapping("/api/business-logo")
    public ResponseEntity<Resource> getBusinessLogo(@RequestParam String businessLogo)
    {
    	try {
    		
    		Path logoPath = businessLogoFolder.resolve(businessLogo);
    		File logoFile = logoPath.toFile();
    		if(!logoFile.exists())
    		{
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    		Resource logoResource = new org.springframework.core.io.FileSystemResource(logoFile);
    		
    		return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Adjust MIME type based on your image type
                    .body(logoResource);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
}
