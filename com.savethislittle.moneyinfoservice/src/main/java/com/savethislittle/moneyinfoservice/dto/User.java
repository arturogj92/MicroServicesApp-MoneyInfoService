package com.savethislittle.moneyinfoservice.dto;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class User {

	private Long id;
	private String userName;
	private String password;
	private String email;
	private EconomyInfo economyInfo;
}
