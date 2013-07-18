package com.qsoft.bankaccount;


public class TransactionDTO {
	private String accountNumber;
	private float amount;
	private long timeStamp;
	private String description;

	public TransactionDTO(String accountNumber, float amount, long timeStamp,
			String description) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.description = description;
	}

	public TransactionDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
