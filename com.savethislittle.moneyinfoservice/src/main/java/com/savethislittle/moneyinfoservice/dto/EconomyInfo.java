package com.savethislittle.moneyinfoservice.dto;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Service
public class EconomyInfo {

	private Long id;
	private double monthlyEarning;
	private double currentMoney;

}
