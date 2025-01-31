package com.alpha.www.Banking.service.impl;

import org.springframework.stereotype.Service;

import com.alpha.www.Banking.dto.AccountDto;
import com.alpha.www.Banking.entity.Account;
import com.alpha.www.Banking.mapper.AccountMapper;
import com.alpha.www.Banking.repository.AccountRepository;
import com.alpha.www.Banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

}
