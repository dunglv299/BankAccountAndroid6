package com.example.bankaccounttest;

import java.util.List;

import android.test.AndroidTestCase;

import com.qsoft.bankaccount.BankAccountDTO;
import com.qsoft.bankaccount.TransactionDTO;
import com.qsoft.bankaccount.TransactionDao;

public class TransactionDaoTest extends AndroidTestCase {
	private TransactionDao transactionDao;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		this.transactionDao = new TransactionDao(getContext(), null);
	}

	public void testInsertTransaction() {
		TransactionDTO transactionDTO = createSampleTransactionDTO();
		long rowId = transactionDao.insert(transactionDTO);
		assertEquals(1, rowId);
	}

	public void testGetTransactionDaoOccurred() {
		TransactionDTO transactionDTO = createSampleTransactionDTO();
		transactionDao.insert(transactionDTO);
		transactionDao.insert(transactionDTO);
		List<TransactionDTO> listTransaction = transactionDao
				.getTransactionDaoOccurred(transactionDTO.getAccountNumber());
		assertEquals(2, listTransaction.size());
	}

	public void testGetTransactionDaoOccurredInTime() {
		TransactionDTO transactionDTO = createSampleTransactionDTO();
		transactionDTO.setTimeStamp(1L);
		transactionDao.insert(transactionDTO);
		transactionDTO.setTimeStamp(2L);
		transactionDao.insert(transactionDTO);
		List<TransactionDTO> listTransaction = transactionDao
				.getTransactionDaoOccurredInTime(
						transactionDTO.getAccountNumber(), 2L, 3L);
		assertEquals(1, listTransaction.size());
	}

	public TransactionDTO createSampleTransactionDTO() {
		TransactionDTO transactionDTO = new TransactionDTO("1234567890", 100,
				System.currentTimeMillis(), "Deposit 100k");
		return transactionDTO;
	}
}
