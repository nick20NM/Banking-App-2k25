package com.alpha.www.Banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		
		// check account exists or not
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
		
		// update the balance
		double total = account.getBalance() + amount;
		account.setBalance(total);
		
		// save to DB
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
		
		// 500 > 100
		if (amount > account.getBalance()) {
			throw new RuntimeException("Insufficient balance");
		}
		
		                      // 1000 - 500 
		Double total = account.getBalance() - amount;
		account.setBalance(total);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts
				.stream()
				.map(account -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}

}
