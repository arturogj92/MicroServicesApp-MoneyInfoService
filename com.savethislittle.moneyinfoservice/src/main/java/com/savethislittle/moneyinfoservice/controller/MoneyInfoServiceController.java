package com.savethislittle.moneyinfoservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethislittle.moneyinfoservice.dto.SumAmountExpensesMonthYear;
import com.savethislittle.moneyinfoservice.dto.TopExpensesView;
import com.savethislittle.moneyinfoservice.dto.User;
import com.savethislittle.moneyinfoservice.proxy.UserRepositoryInfo;
import com.savethislittle.moneyinfoservice.service.MoneyInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController	
public class MoneyInfoServiceController {


	
	@Autowired
	UserRepositoryInfo userRepositoryInfo;
	@Autowired
	MoneyInfoService moneyInfoService;

	@RequestMapping("/user/{email}")
	public User getUserByEmail(@PathVariable String email) {
		log.info("ACTION: getUserByEmail INPUT: => {}", email);
		return userRepositoryInfo.findByEmail(email);
		
	}
	
	@RequestMapping("/money/{email}")
	public double getCurrentMoney(@PathVariable String email) {
		log.info("ACTION: getCurrentMoney INPUT: => {}", email);
		return moneyInfoService.currentMoney(email);
	}
	
	@RequestMapping("/highestexpense/{email}")
	public SumAmountExpensesMonthYear getHighestExpense(@PathVariable String email) {
		return moneyInfoService.highestExpense(email);
	}
	
	@PutMapping("/expenses")
	public ResponseEntity<Void> updateExpenses(@Valid @RequestBody SumAmountExpensesMonthYear expense) {
		log.info("ACTION: updateExpenses INPUT: => {}"," NOTE: " + expense.getNote() + "| USER: " + expense.getEmail());
		userRepositoryInfo.updateExpenses(expense);
		log.info("ACTION: updateExpenses OUTPUT: => {}", "expense updated");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/expensessorted/{email}/{category}")
	public ResponseEntity<List<SumAmountExpensesMonthYear>> getExpensesSorted(@PathVariable String email, @PathVariable String category  ) {
		
		List<SumAmountExpensesMonthYear> list = moneyInfoService.sortHighestExpensesByCategory(category, email);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping("/highestexpensedcategory/{email}")
	public TopExpensesView getHighestExpensedCategory(@PathVariable String email) {
		return moneyInfoService.getHighestExpensedCategory(email);
	}

}
