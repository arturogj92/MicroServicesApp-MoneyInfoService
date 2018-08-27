package com.savethislittle.moneyinfoservice.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Component
public class EconomyInfo {

	private Long id;
	private double monthlyEarning;
	private double currentMoney;

}
