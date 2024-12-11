package com.backinformal.BackInFormal_Backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {
    @Bean
    public Cloudinary geCloudinary(){

        Map config = new HashMap();
        config.put("cloud_name","dh8v09eaj");
        config.put("api_key","341635862862153");
        config.put("api_secret","OxJV4aUCxjhp1ZPN5DHQtJSB0jY");
        config.put("secure",true);

        return new Cloudinary(config);
    }
}
