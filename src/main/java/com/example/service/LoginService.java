package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Login;
import com.example.repository.LoginDao;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;
    
    public Login getLoginByUsername(String username) {
        return loginDao.findByUsername(username);
    }
    
    public boolean saveLogin(Login login) {
    	return loginDao.save(login);
    }
    
    public boolean updatePassword(Login login) {
        return loginDao.update(login);
    }
    
}
