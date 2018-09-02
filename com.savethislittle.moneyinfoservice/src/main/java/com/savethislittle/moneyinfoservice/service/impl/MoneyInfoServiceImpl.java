package com.savethislittle.moneyinfoservice.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.savethislittle.moneyinfoservice.dto.Expenses;
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
	public User getUserByEmail(String email) {
		User findByEmail = userRepositoryInfo.getUserByEmail(email);
		// User user = findById.getBody();
		return findByEmail;
	}

	@Override
	public double getCurrentMoney(String email) {
		User findByEmail = userRepositoryInfo.getUserByEmail(email);
		double money = findByEmail.getEconomyInfo().getCurrentMoney();
		List<Expenses> expensesByEmail = userRepositoryInfo.getAllExpenseByEmail(email);
		double sum = 0.0;

		for (Expenses expense : expensesByEmail) {
			sum += expense.getAmount();
		}

		return money - sum;
	}

	@Override
	public Expenses getHighestExpense(String email) {
		List<Expenses> expensesByEmail = userRepositoryInfo.getAllExpenseByEmail(email);
		Expenses highestExpense = null;
		double highestAmount = 0.00;
		for (Expenses expense : expensesByEmail) {
			if (expense.getAmount() >= highestAmount) {
				highestAmount = expense.getAmount();
				highestExpense = expense;
			}
		}

		return highestExpense;
	}

	@Override
	public List<Expenses> sortHighestExpensesByCategory(String category, String email) {
		List<Expenses> expensesByEmail = userRepositoryInfo.searchExpenseByCategoryAndMail(email, category);
		expensesByEmail.sort(Comparator.comparing(Expenses::getAmount).reversed());
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

	@Override
	public double getAmountMonthlyPaidBySubscriptions(String email, String year) {
		List<Expenses> expensesByEmailAndYear = userRepositoryInfo.getAllExpenseByEmailAndYear(email, year);
		
		
		double monthlyAmount = 0.0;

		for (Expenses expense : expensesByEmailAndYear) {
			if (expense.getType().equals("monthly subscription")) {
				monthlyAmount+=expense.getAmount();
				
			}
			if (expense.getType().equals("anual subscription")) {
				monthlyAmount+=expense.getAmount()/12;
			}
		}

		return monthlyAmount;
	}

	@Override
	public double getAmountAnualPaidBySubscriptions(String email, String year) {
		List<Expenses> expensesByEmailAndYear = userRepositoryInfo.getAllExpenseByEmailAndYear(email, year);
		
		
		double anualAmount = 0.0;

		for (Expenses expense : expensesByEmailAndYear) {
			if (expense.getType().equals("monthly subscription")) {
				anualAmount+=expense.getAmount()*12;
				
			}
			if (expense.getType().equals("anual subscription")) {
				anualAmount+=expense.getAmount();
			}
		}

		return anualAmount;
	}

	// @Override
	// public List<Expenses> sortHighestExpensesByCategory(String category, String
	// email) {
	// List<Expenses> expensesByEmail =
	// userRepositoryInfo.searchExpenseByCategoryAndMail(email, category);
	// List<Expenses> sortedExpenses =
	// expensesByEmail.sort(Comparator.comparing(expensesByEmail::category));
	// return null;
	// }

	// list.sort(Comparator.comparing(AnObject::getAttr));

}
