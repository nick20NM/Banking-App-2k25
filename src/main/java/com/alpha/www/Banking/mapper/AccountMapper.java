package com.alpha.www.Banking.mapper;

import com.alpha.www.Banking.dto.AccountDto;
import com.alpha.www.Banking.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		return new Account(
				accountDto.getId(), 
				accountDto.getAccountHolderName(), 
				accountDto.getBalance());
	}
	
	public static AccountDto mapToAccountDto(Account account) {
		return new AccountDto(
				account.getId(), 
				account.getAccountHolderName(), 
				account.getBalance());
	}
}
