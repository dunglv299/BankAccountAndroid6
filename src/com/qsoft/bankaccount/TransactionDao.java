package com.qsoft.bankaccount;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TransactionDao {
	SQLiteDatabase mDb;

	public TransactionDao(Context context, String dbName) {
		// TODO Auto-generated constructor stub
		DatabaseHelper databaseHelper = new DatabaseHelper(context, dbName);
		this.mDb = databaseHelper.getReadableDatabase();
	}

	public long insert(TransactionDTO transactionDTO) {
		// TODO Auto-generated method stub
		long rowId = -1;
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.ACCOUNT_NUMBER,
				transactionDTO.getAccountNumber());
		values.put(DatabaseHelper.AMOUNT, transactionDTO.getAmount());
		values.put(DatabaseHelper.OPEN_TIMESTAMP, transactionDTO.getTimeStamp());
		values.put(DatabaseHelper.TRANSACTION_DESCRIPTION,
				transactionDTO.getDescription());
		rowId = mDb.insert(DatabaseHelper.TABLE_TRANSACTION, null, values);
		return rowId;
	}

	public List<TransactionDTO> getTransactionDaoOccurred(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TransactionDTO> getTransactionDaoOccurredInTime(
			String accountNumber, long startTime, long stopTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TransactionDTO> getNumberOfTransactionDao(String accountNumber,
			int numberOfTransaction) {
		// TODO Auto-generated method stub
		return null;
	}

}
