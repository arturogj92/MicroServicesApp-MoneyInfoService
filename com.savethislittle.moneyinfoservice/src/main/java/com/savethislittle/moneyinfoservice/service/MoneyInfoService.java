package com.savethislittle.moneyinfoservice.service;

import java.util.List;

import com.savethislittle.moneyinfoservice.dto.SumAmountExpensesMonthYear;
import com.savethislittle.moneyinfoservice.dto.TopExpensesView;
import com.savethislittle.moneyinfoservice.dto.User;

public interface MoneyInfoService {

	public User findByEmail(String email);
	
	public double currentMoney(String email);
	
	public SumAmountExpensesMonthYear highestExpense(String email);
	
	public List<SumAmountExpensesMonthYear> sortHighestExpensesByCategory(String category, String email);
	
	public TopExpensesView getHighestExpensedCategory(String email);
	
}
