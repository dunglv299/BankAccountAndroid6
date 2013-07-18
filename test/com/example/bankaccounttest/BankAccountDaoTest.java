package com.example.bankaccounttest;

import com.qsoft.bankaccount.BankAccountDTO;
import com.qsoft.bankaccount.BankAccountDao;
import com.qsoft.bankaccount.DatabaseHelper;

import android.test.AndroidTestCase;

public class BankAccountDaoTest extends AndroidTestCase {
	private BankAccountDao bankAccountDao;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		bankAccountDao = new BankAccountDao(getContext(), null);
	}

	public void testInsertDate() {
		BankAccountDTO accountDTO = createSampleBankAccount();
		long rowID = bankAccountDao.insert(accountDTO);
		assertEquals(1, rowID);
		assertEquals(1,
				bankAccountDao.getTableSize(DatabaseHelper.TABLE_ACCOUNT));
	}

	public BankAccountDTO createSampleBankAccount() {
		long timeStamp = System.currentTimeMillis();
		return new BankAccountDTO("1234567890", 0, timeStamp);
	}
}
