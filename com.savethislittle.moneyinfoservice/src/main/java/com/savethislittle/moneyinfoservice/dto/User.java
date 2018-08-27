package com.savethislittle.moneyinfoservice.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class User {

	private Long id;
	private String userName;
	private String password;
	private String email;
	private EconomyInfo economyInfo;
}
