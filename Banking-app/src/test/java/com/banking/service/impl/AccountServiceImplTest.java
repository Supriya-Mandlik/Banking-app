package com.banking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.banking.dto.AccountDto;
import com.banking.entity.Account;
import com.banking.mapper.AccountMapper;
import com.banking.repository.AccountRepository;

@SpringBootTest
public class AccountServiceImplTest {
	
	@InjectMocks
	AccountServiceImpl accountServiceImpl;
	
	@Mock
	AccountRepository accountRepository;
	
	
	@Test
	public void getAccountByIdTest() {
		

		when(accountRepository.findById((long) 1)).thenReturn(createAccountStub());
		
		AccountDto accountByIdtest = accountServiceImpl.getAccountById(1);
		
		assertEquals(accountByIdtest.getAccountHolderName(), "supriya");
		
	}
	
public void getAccountByIdTestWithException() {
		

		when(accountRepository.findById((long) 1)).thenReturn(createAccountStubWithException());
		
		RuntimeException assertThrows2 = assertThrows(RuntimeException.class, () -> accountServiceImpl.getAccountById(1));
		
		assertEquals(assertThrows2.getMessage(), "Account does not exists");
		
	}

  @Test
  public void getAllAccountsTest() {
	  
	 when(accountRepository.findAll()).thenReturn(Stream
	.of(new Account(2, "saurabh", 10000),new Account(3, "sanjay", 100000))
	.collect(Collectors.toList()));
	 
	 assertEquals(2, accountServiceImpl.getAllAccounts().size());
	  
  }
	
	private Optional<Account> createAccountStub() {
		Account account = new Account(1, "supriya", 100000);
		return Optional.of(account);
	}
	
	private Optional<Account> createAccountStubWithException() {
		Account account = new Account(1, "supriya", 100000);
		return Optional.of(account);
	}

}
