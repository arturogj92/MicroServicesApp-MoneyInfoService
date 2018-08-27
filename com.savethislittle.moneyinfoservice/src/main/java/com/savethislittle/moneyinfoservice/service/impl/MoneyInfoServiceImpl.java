package com.savethislittle.moneyinfoservice.service.impl;

import org.springframework.stereotype.Service;

import com.savethislittle.moneyinfoservice.dto.User;
import com.savethislittle.moneyinfoservice.proxy.UserRepositoryInfo;
import com.savethislittle.moneyinfoservice.service.MoneyInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
@Service
public class MoneyInfoServiceImpl implements MoneyInfoService{

	UserRepositoryInfo userRepositoryInfo;
	
	@Override
	public User findByEmail(String email) {
		User findByEmail = userRepositoryInfo.findByEmail(email);
		//User user = findById.getBody();
		return findByEmail;
	}
	
}
