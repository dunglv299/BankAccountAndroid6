package com.qsoft.bankaccount;


import java.util.List;

public class Transaction {

	private static TransactionDao transactionDao;
	private BankAccount bankAccount = new BankAccount();

	public void setTransactionDao(TransactionDao mockTransactionDao) {
		// TODO Auto-generated method stub
		Transaction.transactionDao = mockTransactionDao;
	}

	public TransactionDTO createTransaction(String accountNumber,
			long timeStamp, float amount, String description) {
		// TODO Auto-generated method stub
		// BankAccountDTO accountDTO = bankAccount.getAccount(accountNumber);
		TransactionDTO transactionDTO = new TransactionDTO(accountNumber,
				amount, timeStamp, description);
		transactionDao.insert(transactionDTO);
		return transactionDTO;
	}

	public List<TransactionDTO> getTransactionOccurred(String accountNumber) {
		// TODO Auto-generated method stub
		return transactionDao.getTransactionDaoOccurred(accountNumber);
	}

	public List<TransactionDTO> getTransactionOccurredInTime(
			String accountNumber, long startTime, long stopTime) {
		// TODO Auto-generated method stub
		return transactionDao.getTransactionDaoOccurredInTime(accountNumber,
				startTime, stopTime);
	}

	public List<TransactionDTO> getNumberOfTransaction(String accountNumber,
			int numberOfTransaction) {
		// TODO Auto-generated method stub
		return transactionDao.getNumberOfTransactionDao(accountNumber,
				numberOfTransaction);
	}
}
