package com.alpha.www.Banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.www.Banking.dto.AccountDto;
import com.alpha.www.Banking.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		return ResponseEntity.ok(accountService.getAccountById(id));
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(
			@PathVariable Long id, 
			@RequestBody Map<String, Double> request){
		
		Double amount = request.get("amount");
		AccountDto account = accountService.deposit(id, amount);
		
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(
			@PathVariable Long id, 
			@RequestBody Map<String, Double> request){
		
		Double amount = request.get("amount");
		AccountDto account = accountService.withdraw(id, amount);
		return ResponseEntity.ok(account);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		return ResponseEntity.ok(accountService.getAllAccounts());
	}
 }
