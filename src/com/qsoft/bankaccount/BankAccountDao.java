package com.qsoft.bankaccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class BankAccountDao {
	private SQLiteDatabase mDB;

	public BankAccountDao(Context context, String dbName) {
		// TODO Auto-generated constructor stub
		DatabaseHelper mHelper = new DatabaseHelper(context, dbName);
		this.mDB = mHelper.getWritableDatabase();
	}

	public void save(BankAccountDTO accountDTO) {
		// TODO Auto-generated method stub

	}

	public BankAccountDTO getAccountDao(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public long insert(BankAccountDTO accountDTO) {
		// TODO Auto-generated method stub
		long rowID = -1;
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.ACCOUNT_NUMBER,
				accountDTO.getAccountNumber());
		contentValues.put(DatabaseHelper.BALANCE, accountDTO.getBalance());
		contentValues.put(DatabaseHelper.OPEN_TIMESTAMP,
				accountDTO.getTimeStamp());
		rowID = mDB.insert(DatabaseHelper.TABLE_ACCOUNT, null, contentValues);
		return rowID;
	}

	public int getTableSize(String tableAccount) {
		// TODO Auto-generated method stub
		int numRows = (int) DatabaseUtils.queryNumEntries(mDB, tableAccount);
		return numRows;
	}

}
