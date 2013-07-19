package com.qsoft.bankaccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		String whereClause = DatabaseHelper.ACCOUNT_NUMBER + "="
				+ accountDTO.getAccountNumber();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.ACCOUNT_NUMBER, accountDTO.getAccountNumber());
		values.put(DatabaseHelper.BALANCE, accountDTO.getBalance());
		values.put(DatabaseHelper.OPEN_TIMESTAMP, accountDTO.getTimeStamp());
		mDB.update(DatabaseHelper.TABLE_ACCOUNT, values, whereClause, null);
	}

	public BankAccountDTO getAccountDao(String accountNumber) {
		// TODO Auto-generated method stub
		BankAccountDTO accountDTO = new BankAccountDTO();
		String whereClause = DatabaseHelper.ACCOUNT_NUMBER + "= ?";
		Cursor cursor = mDB.query(DatabaseHelper.TABLE_ACCOUNT, new String[] {
				DatabaseHelper.ID, DatabaseHelper.ACCOUNT_NUMBER,
				DatabaseHelper.BALANCE, DatabaseHelper.OPEN_TIMESTAMP },
				whereClause, new String[] { accountNumber }, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				accountDTO.setAccountNumber(cursor.getString(1));
				accountDTO.setBalance(cursor.getLong(2));
				accountDTO.setTimeStamp(cursor.getLong(3));
			} while (cursor.moveToNext());

		}
		return accountDTO;
	}

	public long insert(BankAccountDTO accountDTO) {
		// TODO Auto-generated method stub

		long rowID = -1;
		ContentValues contentValues = new ContentValues();
		if (!checkExistRecord(accountDTO)) {
			contentValues.put(DatabaseHelper.ACCOUNT_NUMBER,
					accountDTO.getAccountNumber());
			contentValues.put(DatabaseHelper.BALANCE, accountDTO.getBalance());
			contentValues.put(DatabaseHelper.OPEN_TIMESTAMP,
					accountDTO.getTimeStamp());
			rowID = mDB.insert(DatabaseHelper.TABLE_ACCOUNT, null,
					contentValues);
		}
		return rowID;
	}

	public boolean checkExistRecord(BankAccountDTO accountDTO) {
		Cursor cursor = mDB.rawQuery("SELECT * FROM "
				+ DatabaseHelper.TABLE_ACCOUNT + " WHERE "
				+ DatabaseHelper.ACCOUNT_NUMBER + "=?",
				new String[] { accountDTO.getAccountNumber() });
		boolean exists = (cursor.getCount() > 0);
		cursor.close();
		return exists;
	}

	public int getTableSize(String tableAccount) {
		// TODO Auto-generated method stub
		int numRows = (int) DatabaseUtils.queryNumEntries(mDB, tableAccount);
		return numRows;
	}

}
