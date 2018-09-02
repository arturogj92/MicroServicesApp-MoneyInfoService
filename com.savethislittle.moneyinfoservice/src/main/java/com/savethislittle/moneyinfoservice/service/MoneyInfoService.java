package com.savethislittle.moneyinfoservice.service;

import java.util.List;

import com.savethislittle.moneyinfoservice.dto.Expenses;
import com.savethislittle.moneyinfoservice.dto.TopExpensesView;
import com.savethislittle.moneyinfoservice.dto.User;

public interface MoneyInfoService {

	public User getUserByEmail(String email);

	public double getCurrentMoney(String email);

	public Expenses getHighestExpense(String email);

	public List<Expenses> sortHighestExpensesByCategory(String category, String email);

	public TopExpensesView getHighestExpensedCategory(String email);

	public double getAmountMonthlyPaidBySubscriptions(String email, String year);

	public double getAmountAnualPaidBySubscriptions(String email, String year);

}
