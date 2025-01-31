package com.alpha.www.Banking.service;

import com.alpha.www.Banking.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
}
