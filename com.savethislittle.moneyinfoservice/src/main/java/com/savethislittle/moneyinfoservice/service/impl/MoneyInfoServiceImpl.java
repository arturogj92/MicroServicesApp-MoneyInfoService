package com.savethislittle.moneyinfoservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.savethislittle.moneyinfoservice.dto.Expenses;
import com.savethislittle.moneyinfoservice.dto.User;
import com.savethislittle.moneyinfoservice.proxy.UserRepositoryInfo;
import com.savethislittle.moneyinfoservice.service.MoneyInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class MoneyInfoServiceImpl implements MoneyInfoService {

	UserRepositoryInfo userRepositoryInfo;

	@Override
	public User findByEmail(String email) {
		User findByEmail = userRepositoryInfo.findByEmail(email);
		// User user = findById.getBody();
		return findByEmail;
	}

	@Override
	public double currentMoney(String email) {
		User findByEmail = userRepositoryInfo.findByEmail(email);
		double money = findByEmail.getEconomyInfo().getCurrentMoney();
		List<Expenses> expensesByEmail = userRepositoryInfo.findExpensesByEmail(email);
		double sum=0.0;
		
		for(Expenses expense : expensesByEmail){
			sum+=expense.getAmount();
		}
		
		
		return money-sum;
	}

}
