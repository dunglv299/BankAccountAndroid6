package com.qsoft.bankaccount;


import java.util.Calendar;
import java.util.List;

public class BankAccount {
	private static BankAccountDao bankAccountDao;

	private BankAccountDTO accountDTO;
	private Transaction transaction;
	private static Calendar calendar;

	public BankAccountDTO openAccount(String accountNumber, float balance) {
		// TODO Auto-generated method stub
		BankAccountDTO accountDTO = new BankAccountDTO(accountNumber, balance);
		accountDTO.setAccountNumber(accountNumber);
		accountDTO.setBalance(balance);
		long timeStamp = calendar.getTimeInMillis();
		accountDTO.setTimeStamp(timeStamp);
		bankAccountDao.save(accountDTO);

		return accountDTO;
	}

	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		// TODO Auto-generated method stub
		BankAccount.bankAccountDao = bankAccountDao;
	}

	public void setCalendar(Calendar mockCalendar) {
		// TODO Auto-generated method stub
		BankAccount.calendar = mockCalendar;
	}

	public BankAccountDTO getAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return bankAccountDao.getAccountDao(accountNumber);
	}

	public void deposit(String accountNumber, float amount, String description) {
		// TODO Auto-generated method stub
		accountDTO = getAccount(accountNumber);
		accountDTO.setBalance(accountDTO.getBalance() + amount);
		bankAccountDao.save(accountDTO);
		Long timeStamp = calendar.getTimeInMillis();
		transaction = new Transaction();
		transaction.createTransaction(accountNumber, timeStamp, amount,
				description);
	}

	public void withDraw(String accountNumber, float amount, String description) {
		// TODO Auto-generated method stub
		accountDTO = getAccount(accountNumber);
		accountDTO.setBalance(accountDTO.getBalance() - amount);
		bankAccountDao.save(accountDTO);
		transaction = new Transaction();
		Long timeStamp = calendar.getTimeInMillis();
		transaction.createTransaction(accountNumber, timeStamp, -amount,
				description);
	}

	public List<TransactionDTO> getTransactionOccurred(String accountNumber) {
		// TODO Auto-generated method stub
		accountDTO = getAccount(accountNumber);
		transaction = new Transaction();
		return transaction.getTransactionOccurred(accountNumber);
	}

	public List<TransactionDTO> getTransactionOccurredInTime(
			String accountNumber, long startTime, long stopTime) {
		// TODO Auto-generated method stub
		accountDTO = getAccount(accountNumber);
		transaction = new Transaction();
		return transaction.getTransactionOccurredInTime(accountNumber,
				startTime, stopTime);
	}

	public List<TransactionDTO> getNumberOfTransaction(String accountNumber,
			int numberOfTransaction) {
		// TODO Auto-generated method stub
		accountDTO = getAccount(accountNumber);
		transaction = new Transaction();
		return transaction.getNumberOfTransaction(accountNumber,
				numberOfTransaction);
	}
}
