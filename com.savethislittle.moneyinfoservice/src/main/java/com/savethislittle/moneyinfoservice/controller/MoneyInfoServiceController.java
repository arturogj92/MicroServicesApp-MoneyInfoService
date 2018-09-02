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

import com.savethislittle.moneyinfoservice.dto.Expenses;
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
		return userRepositoryInfo.getUserByEmail(email);
		
	}
	
	@RequestMapping("/money/{email}")
	public double getCurrentMoney(@PathVariable String email) {
		log.info("ACTION: getCurrentMoney INPUT: => {}", email);
		return moneyInfoService.getCurrentMoney(email);
	}
	
	@RequestMapping("/highestexpense/{email}")
	public Expenses getHighestExpense(@PathVariable String email) {
		return moneyInfoService.getHighestExpense(email);
	}
	
	@RequestMapping("/expensessorted/{email}/{category}")
	public ResponseEntity<List<Expenses>> getExpensesSorted(@PathVariable String email, @PathVariable String category  ) {
		
		List<Expenses> list = moneyInfoService.sortHighestExpensesByCategory(category, email);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping("/highestexpensedcategory/{email}")
	public TopExpensesView getHighestExpensedCategory(@PathVariable String email) {
		return moneyInfoService.getHighestExpensedCategory(email);
	}
	
	@RequestMapping("/monthlysubscriptions/{email}/{year}")
	public double getAmountMonthlyPaidBySubscriptions(@PathVariable String email, @PathVariable String year) {
		return moneyInfoService.getAmountMonthlyPaidBySubscriptions(email, year);
	}
	
	@RequestMapping("/anualsubscriptions/{email}/{year}")
	public double getAmountAnualPaidBySubscriptions(@PathVariable String email, @PathVariable String year) {
		return moneyInfoService.getAmountAnualPaidBySubscriptions(email, year);
	}
	

}
