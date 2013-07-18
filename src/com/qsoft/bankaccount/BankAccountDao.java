package com.qsoft.bankaccount;

import android.content.Context;
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
		return 0;
	}

}
