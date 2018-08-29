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
import com.savethislittle.moneyinfoservice.dto.User;
import com.savethislittle.moneyinfoservice.proxy.UserRepositoryInfo;
import com.savethislittle.moneyinfoservice.service.MoneyInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController	
public class MoneyInfoServiceController {


//
//	@RequestMapping("/user/feign/{email}")
//	public User findme(@PathVariable String email) {
//		return moneyInfoService.findByEmail(email);
//	}
	
	@Autowired
	UserRepositoryInfo userRepositoryInfo;
	@Autowired
	MoneyInfoService moneyInfoService;

	@RequestMapping("/user/{email}")
	public User getUserByEmail(@PathVariable String email) {
		log.info("ACTION: getUserByEmail INPUT: => {}", email);
		return userRepositoryInfo.findByEmail(email);
		
	}
	
	@RequestMapping("/expenses/{email}")
	public double getCurrentMoney(@PathVariable String email) {
		log.info("ACTION: getCurrentMoney INPUT: => {}", email);
		return moneyInfoService.currentMoney(email);
	}
	
	@RequestMapping("/highestexpense/{email}")
	public Expenses getHighestExpense(@PathVariable String email) {
		return moneyInfoService.highestExpense(email);
	}
	
	@PutMapping("/expenses")
	public ResponseEntity<Void> updateExpenses(@Valid @RequestBody Expenses expense) {
		log.info("ACTION: updateExpenses INPUT: => {}"," NOTE: " + expense.getNote() + "| USER: " + expense.getEmail());
		userRepositoryInfo.updateExpenses(expense);
		log.info("ACTION: updateExpenses OUTPUT: => {}", "expense updated");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/expensessorted/{email}/{category}")
	public ResponseEntity<List<Expenses>> getExpensesSorted(@PathVariable String email, @PathVariable String category  ) {
		
		List<Expenses> list = moneyInfoService.sortHighestExpensesByCategory(category, email);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	

}
