package com.backinformal.BackInFormal_Backend.config;

import com.backinformal.BackInFormal_Backend.entity.UserMaster;
import com.backinformal.BackInFormal_Backend.repository.UserMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMasterDetailsService implements UserDetailsService {

    @Autowired
    private UserMasterRepository repoUser;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserMaster> userInfo =repoUser.findByUserName(username);

      return userInfo.map(UserMasterDetails::new).
                orElseThrow(()->new UsernameNotFoundException("User Not Found : "+username));
    }
}
