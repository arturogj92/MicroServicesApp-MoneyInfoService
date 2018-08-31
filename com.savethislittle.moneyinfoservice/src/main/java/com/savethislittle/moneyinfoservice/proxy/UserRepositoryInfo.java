package com.savethislittle.moneyinfoservice.proxy;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.savethislittle.moneyinfoservice.dto.SumAmountExpensesMonthYear;
import com.savethislittle.moneyinfoservice.dto.TopExpensesView;
import com.savethislittle.moneyinfoservice.dto.User;

@FeignClient("userinfo-repository")
public interface UserRepositoryInfo {

	@RequestMapping("/user/{email}")
	public User findByEmail(@PathVariable(value = "email") String email);

	@RequestMapping("/expenses/{email}")
	public List<SumAmountExpensesMonthYear> findExpensesByEmail(@PathVariable(value = "email") String email);

	@PutMapping("/expenses")
	public ResponseEntity<Void> updateExpenses(@Valid @RequestBody SumAmountExpensesMonthYear expense);

	@RequestMapping("/expenses/{email}/{category}")
	public List<SumAmountExpensesMonthYear> searchExpenseByCategoryAndMail(@PathVariable(value="email") String email, @PathVariable(value="category") String category);   
		
	@RequestMapping("/totalamountcategory/{email}")
	public List<TopExpensesView> findTopExpensesByEmail(@PathVariable(value="email") String email);	
		
}
