package com.savethislittle.moneyinfoservice.dto;



import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Expenses {

	
	private Long id;
	private String type;
	private String year;
	private String month;
	private String category;
	private String subCategory;
	private String note;
	private double amount;
	private String email;
	
}
