package com.qsoft.bankaccount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	public static final String TABLE_ACCOUNT = "ACCOUNT";
	public static final String ID = "_ID";
	public static final String BALANCE = "BALANCE";
	public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
	public static final String OPEN_TIMESTAMP = "OPEN_TIMESTAMP";
	private static final String CREATE_TABLE_BANKACCOUNT = "CREATE TABLE "
			+ TABLE_ACCOUNT + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ ACCOUNT_NUMBER + " TEXT NOT NULL," + BALANCE + " TEXT NOT NULL,"
			+ OPEN_TIMESTAMP + " LONG)";

	public static final String TABLE_TRANSACTION = "TRANSACTION_ACCOUNT";
	public static final String TRANSACTION_ID = "TRANSACTION_ID";
	public static final String AMOUNT = "AMOUNT";
	public static final String TRANSACTION_DESCRIPTION = "TRANSACTION_DESCRIPTION";

	private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE "
			+ TABLE_TRANSACTION + " (" + ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACCOUNT_NUMBER
			+ " TEXT NOT NULL," + AMOUNT + " FLOAT," + OPEN_TIMESTAMP
			+ " LONG," + TRANSACTION_DESCRIPTION + " TEXT)";

	public DatabaseHelper(Context context, String dbName) {
		super(context, dbName, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_BANKACCOUNT);
		db.execSQL(CREATE_TABLE_TRANSACTION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_ACCOUNT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
	}

}
