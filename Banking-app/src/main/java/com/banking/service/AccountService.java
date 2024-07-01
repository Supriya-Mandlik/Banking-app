package com.banking.service;

import java.util.List;

import com.banking.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(long id);
	
	AccountDto deposit(long id , double amount);
	
	AccountDto withdraw(long id , double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(long id);
	

}
