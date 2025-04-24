package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Authority;
import com.example.repository.AuthorityDao;

@Service
public class AuthorityService {
	@Autowired
    private AuthorityDao authorityDao;
	
	public boolean saveAuthority(Authority authority) {
		return authorityDao.save(authority);
	}
	
}
