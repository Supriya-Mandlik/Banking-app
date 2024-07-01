package com.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dto.AccountDto;
import com.banking.entity.Account;
import com.banking.mapper.AccountMapper;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
   @Autowired
	private AccountRepository accountRepository;

/*	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	} */

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(long id) {
		
		Account account =accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(long id, double amount) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
	double total = account.getBalance() + amount;
	 account.setBalance(total);
	 Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(long id, double amount) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exists"));
	 
		if (account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		double total = account.getBalance()-amount;
		
		account.setBalance(total);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
	return	accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
			.collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(long id) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exists"));
		
		accountRepository.deleteById(id);
		
		
	}

}
