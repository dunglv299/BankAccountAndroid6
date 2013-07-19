package com.example.bankaccounttest;

import android.test.AndroidTestCase;

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

	public TransactionDTO createSampleTransactionDTO() {
		TransactionDTO transactionDTO = new TransactionDTO("1234567890", 100,
				System.currentTimeMillis(), "Deposit 100k");
		return transactionDTO;
	}
}
