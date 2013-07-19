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

	public void testGetAccountByNumber() {
		BankAccountDTO accountDTO = createSampleBankAccount();
		accountDTO.setBalance(100);
		bankAccountDao.insert(accountDTO);
		BankAccountDTO accDTOFromDB = bankAccountDao.getAccountDao(accountDTO
				.getAccountNumber());
		assertTrue(accDTOFromDB != null);
		assertEquals(100, accDTOFromDB.getBalance(), 0.01);
	}

	public void testInsertDuplicateRecord() {
		BankAccountDTO accountDTO1 = createSampleBankAccount();
		BankAccountDTO accountDTO2 = createSampleBankAccount();
		long rowId1 = bankAccountDao.insert(accountDTO1);
		long rowId2 = bankAccountDao.insert(accountDTO2);
		assertEquals(1, rowId1);
		assertEquals(-1, rowId2);
		assertEquals(1,
				bankAccountDao.getTableSize(DatabaseHelper.TABLE_ACCOUNT));
	}

	public void testUpdateAccount() {
		BankAccountDTO accountDTO = createSampleBankAccount();
		bankAccountDao.insert(accountDTO);
		accountDTO.setBalance(100);
		bankAccountDao.save(accountDTO);

		BankAccountDTO accountDTOFromDB = bankAccountDao
				.getAccountDao(accountDTO.getAccountNumber());
		assertEquals(100, accountDTOFromDB.getBalance(), 0.01);

	}

	public BankAccountDTO createSampleBankAccount() {
		long timeStamp = System.currentTimeMillis();
		return new BankAccountDTO("1234567890", 0, timeStamp);
	}
}
