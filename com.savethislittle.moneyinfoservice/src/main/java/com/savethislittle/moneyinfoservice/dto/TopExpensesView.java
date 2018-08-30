package com.savethislittle.moneyinfoservice.dto;


import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class TopExpensesView {

	
	
	private Long id;
	private String email;
	private String category;
	private double amount;
	
}
