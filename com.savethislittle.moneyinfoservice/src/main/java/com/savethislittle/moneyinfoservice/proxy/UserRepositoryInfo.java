package com.savethislittle.moneyinfoservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.savethislittle.moneyinfoservice.dto.Expenses;
import com.savethislittle.moneyinfoservice.dto.User;

@FeignClient("userinfo-repository")
public interface UserRepositoryInfo {

	@RequestMapping("/user/{email}")
	public User findByEmail(@PathVariable(value = "email") String email);
	
	@RequestMapping("/expenses/{email}")
	public List<Expenses>  findExpensesByEmail(@PathVariable(value = "email") String email);
}
