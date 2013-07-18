package com.example.bankaccounttest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;

import com.qsoft.bankaccount.BankAccount;
import com.qsoft.bankaccount.BankAccountDTO;
import com.qsoft.bankaccount.BankAccountDao;
import com.qsoft.bankaccount.Transaction;
import com.qsoft.bankaccount.TransactionDTO;
import com.qsoft.bankaccount.TransactionDao;

public class BankAccountTest extends TestCase {
	BankAccount bankAccount;
	private Transaction transaction;

	private BankAccountDao mockBankAccountDao = mock(BankAccountDao.class);
	private TransactionDao mockTransactionDao = mock(TransactionDao.class);
	private Calendar mockCalendar = mock(Calendar.class);

	@Override
	public void setUp() throws Exception {
		super.setUp();
		bankAccount = new BankAccount();
		transaction = new Transaction();
		reset(mockBankAccountDao);
		reset(mockTransactionDao);
		reset(mockCalendar);
		bankAccount.setBankAccountDao(mockBankAccountDao);
		transaction.setTransactionDao(mockTransactionDao);
		bankAccount.setCalendar(mockCalendar);
	}

	// Step 1
	public void testOpenAccountWithZeroBalance() {
		BankAccountDTO accountDTO = bankAccount.openAccount("1234567890", 0);
		assertEquals(0, accountDTO.getBalance(), 0.01);
		assertEquals("1234567890", accountDTO.getAccountNumber());
	}

	// Step 2
	public void testGetAccountFromAccountNumber() {
		String accountNumber = "1234567890";
		bankAccount.getAccount(accountNumber);
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		verify(mockBankAccountDao).getAccountDao(argument.capture());
		assertEquals("1234567890", argument.getValue());
	}

	// Step 3
	public void testDepositAccount() {
		String accountNumber = "1234567890";
		float balance = 100f;
		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber,
				balance);
		when(mockBankAccountDao.getAccountDao(accountNumber)).thenReturn(
				accountDTO);
		bankAccount.deposit(accountNumber, 100f, "Deposit 100k");
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDao, times(2)).save(argument.capture());
		assertEquals(200, argument.getValue().getBalance(), 0.01);
		assertEquals(accountNumber, argument.getValue().getAccountNumber());
	}

	// Step 4
	public void testDepositTransaction() {
		String accountNumber = "1234567890";
		float amount = 100f;
		long timeStamp = System.currentTimeMillis();
		String description = "deposit 100k";
		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber, 0);
		when(mockBankAccountDao.getAccountDao(accountNumber)).thenReturn(
				accountDTO);
		when(mockCalendar.getTimeInMillis()).thenReturn(timeStamp);
		bankAccount.deposit(accountNumber, amount, description);
		ArgumentCaptor<TransactionDTO> argument = ArgumentCaptor
				.forClass(TransactionDTO.class);
		verify(mockTransactionDao).insert(argument.capture());
		assertEquals(100, argument.getValue().getAmount(), 0.01);
		assertEquals(timeStamp, argument.getValue().getTimeStamp(), 0.01);
	}

	// Step 5
	public void testWithDrawAccount() {
		String accountNumber = "1234567890";
		float balance = 200f;
		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber,
				balance);
		when(mockBankAccountDao.getAccountDao(accountNumber)).thenReturn(
				accountDTO);
		bankAccount.withDraw(accountNumber, 100f, "Withdraw 100k");
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDao, times(2)).save(argument.capture());
		assertEquals(100, argument.getValue().getBalance(), 0.01);
		assertEquals(accountNumber, argument.getValue().getAccountNumber());
	}

	// Step 6
	public void testWithDrawTransaction() {
		String accountNumber = "1234567890";
		float amount = 100f;
		long timeStamp = System.currentTimeMillis();
		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber, 0);
		when(mockBankAccountDao.getAccountDao(accountNumber)).thenReturn(
				accountDTO);
		when(mockCalendar.getTimeInMillis()).thenReturn(timeStamp);
		bankAccount.withDraw(accountNumber, amount, "Withdraw 100k");
		ArgumentCaptor<TransactionDTO> argument = ArgumentCaptor
				.forClass(TransactionDTO.class);
		verify(mockTransactionDao).insert(argument.capture());
		assertEquals(-100, argument.getValue().getAmount(), 0.01);
		assertEquals(timeStamp, argument.getValue().getTimeStamp(), 0.01);
	}

	// Step 7
	public void testGetTransactionOccurred() {
		String accountNumber = "0123456789";
		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber, 0);
		when(mockBankAccountDao.getAccountDao(accountNumber)).thenReturn(
				accountDTO);
		bankAccount.getTransactionOccurred(accountNumber);
		verify(mockTransactionDao).getTransactionDaoOccurred(accountNumber);
	}

	// Step 8
	public void testGetTransactionOccurredIntime() {
		String accountNumber = "0123456789";
		long startTime = 1l;
		long stopTime = 2l;
		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber, 0);
		when(mockBankAccountDao.getAccountDao(accountNumber)).thenReturn(
				accountDTO);
		bankAccount.getTransactionOccurredInTime(accountNumber, startTime,
				stopTime);
		verify(mockTransactionDao).getTransactionDaoOccurredInTime(
				accountNumber, startTime, stopTime);
	}

	// Step 9
	public void testGetNumberOfTransaction() {
		String accountNumber = "0123456789";
		int numberOfTransaction = 5;
		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber, 0);
		when(mockBankAccountDao.getAccountDao(accountNumber)).thenReturn(
				accountDTO);

		bankAccount.getNumberOfTransaction(accountNumber, numberOfTransaction);

		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockTransactionDao).getNumberOfTransactionDao(accountNumber,
				numberOfTransaction);
	}

	// Step 10
	public void testOpenAccountAndSaveTimeStampToDB() {
		String accountNumber = "0123456789";
		long timeStamp = System.currentTimeMillis();
		when(mockCalendar.getTimeInMillis()).thenReturn(timeStamp);

		BankAccountDTO accountDTO = bankAccount.openAccount(accountNumber, 0);
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDao).save(argument.capture());
		assertEquals(accountNumber, argument.getValue().getAccountNumber());
		assertTrue(argument.getValue().getTimeStamp() != 0);
		assertEquals(timeStamp, argument.getValue().getTimeStamp());
	}
}
