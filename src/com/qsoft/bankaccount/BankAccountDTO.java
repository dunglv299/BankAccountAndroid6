package com.qsoft.bankaccount;

public class BankAccountDTO {

	private String accountNumber;
	private float balance;
	private long timeStamp;

	public BankAccountDTO() {
		super();
	}

	public BankAccountDTO(String accountNumber, float balance, long timeStamp) {
		// TODO Auto-generated constructor stub
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
