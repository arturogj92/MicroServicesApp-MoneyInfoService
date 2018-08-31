package com.savethislittle.moneyinfoservice.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.savethislittle.moneyinfoservice.dto.SumAmountExpensesMonthYear;
import com.savethislittle.moneyinfoservice.dto.TopExpensesView;
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
		List<SumAmountExpensesMonthYear> expensesByEmail = userRepositoryInfo.findExpensesByEmail(email);
		double sum = 0.0;

		for (SumAmountExpensesMonthYear expense : expensesByEmail) {
			sum += expense.getAmount();
		}

		return money - sum;
	}

	@Override
	public SumAmountExpensesMonthYear highestExpense(String email) {
		List<SumAmountExpensesMonthYear> expensesByEmail = userRepositoryInfo.findExpensesByEmail(email);
		SumAmountExpensesMonthYear highestExpense = null;
		double highestAmount = 0.00;
		for (SumAmountExpensesMonthYear expense : expensesByEmail) {
			if(expense.getAmount()>=highestAmount) {
				highestAmount=expense.getAmount();
				highestExpense = expense;
			}
		}
		
		return highestExpense;
	}

	@Override
	public List<SumAmountExpensesMonthYear> sortHighestExpensesByCategory(String category, String email) {
		List<SumAmountExpensesMonthYear> expensesByEmail = userRepositoryInfo.searchExpenseByCategoryAndMail(email, category);
		expensesByEmail.sort(Comparator.comparing(SumAmountExpensesMonthYear::getAmount).reversed());
		return expensesByEmail;
	}

	@Override
	public TopExpensesView getHighestExpensedCategory(String email) {
		List<TopExpensesView> expenses = userRepositoryInfo.findTopExpensesByEmail(email);
		
		if (!expenses.isEmpty()) {
			return expenses.get(0);
		}
		
		return null;
	}

//	@Override
//	public List<Expenses> sortHighestExpensesByCategory(String category, String email) {
//		List<Expenses> expensesByEmail = userRepositoryInfo.searchExpenseByCategoryAndMail(email, category);
//		List<Expenses> sortedExpenses = expensesByEmail.sort(Comparator.comparing(expensesByEmail::category));
//		return null;
//	}
	
	
//	list.sort(Comparator.comparing(AnObject::getAttr));
	

}
