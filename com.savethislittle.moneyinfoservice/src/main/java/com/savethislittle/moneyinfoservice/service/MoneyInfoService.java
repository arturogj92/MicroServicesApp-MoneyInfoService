package com.savethislittle.moneyinfoservice.service;

import com.savethislittle.moneyinfoservice.dto.User;

public interface MoneyInfoService {

	public User findByEmail(String email);
}
