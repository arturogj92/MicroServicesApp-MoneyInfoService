package com.savethislittle.moneyinfoservice.service;

import com.savethislittle.moneyinfoservice.dto.Expenses;
import com.savethislittle.moneyinfoservice.dto.User;

public interface MoneyInfoService {

	public User findByEmail(String email);
	public double currentMoney(String email);
	public Expenses highestExpense(String email);
	
}
