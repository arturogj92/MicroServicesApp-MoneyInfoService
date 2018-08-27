package com.savethislittle.moneyinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethislittle.moneyinfoservice.dto.User;
import com.savethislittle.moneyinfoservice.proxy.UserRepositoryInfo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController	
public class MoneyInfoServiceController {

//	MoneyInfoService moneyInfoService;
//
//	@RequestMapping("/user/feign/{email}")
//	public User findme(@PathVariable String email) {
//		return moneyInfoService.findByEmail(email);
//	}
	
	@Autowired
	UserRepositoryInfo userRepositoryInfo;

	@RequestMapping("/user/{email}")
	public User getUserByEmail(@PathVariable String email) {
		log.info("ACTION: getUserByEmail INPUT: => {}", email);
		return userRepositoryInfo.findByEmail(email);
		
	}

}
