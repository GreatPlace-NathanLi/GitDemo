package com.shanbao.client;

import java.util.List;

import com.shanbao.SBDistributePool;
import com.shanbao.SBRule;
import com.shanbao.SBTrade;

public class Account {
	
	public enum AccountType{ 
		善宝使者,传递使者,消费商,商家
	}
	
	private String accountName;
	
	private String entryDate;
	private AccountType type;
	
	private double activeSBAmount;
	private double disactiveSBAmount;
	
	private double balance;//余额
	private double postponeAmount;//暂缓金额
	
	private Account upperAccount;//推荐人账户
	private Account bisAccount;//商家账户
	
	private List<AccountDailyDetails> dailyDetailsList;
	
	private SBDistributePool sbDistributePool;
	
	private SBRule rule;
	
	public Account() {
		super();
	}
	
	public Account(String accountName, String entryDate, AccountType type, SBRule rule) {
		super();
		this.accountName = accountName;
		this.entryDate = entryDate;
		this.type = type;
		this.rule = rule;
	}

	public Account(String accountName, String entryDate, AccountType type, double activeSBAmount, double disactiveSBAmount,
			double balance, double postponeAmount, Account upperAccount, SBRule rule) {
		this(accountName, entryDate, type, rule);
		this.activeSBAmount = activeSBAmount;
		this.disactiveSBAmount = disactiveSBAmount;
		this.balance = balance;
		this.postponeAmount = postponeAmount;
		this.upperAccount = upperAccount;
	}

	public void addTrade(int dateInInt, double moneyAmount, String status) {
		double newSbAmount = rule.getSbAmountByMoneyAmount(moneyAmount, type);
		SBTrade sbTrade = new SBTrade(dateInInt, newSbAmount, status);
		double activatedSbAmount = sbDistributePool.addTrade(sbTrade);
		//System.out.println("Type " + type + "- 新增善宝：" + String.format("%.4f", newSbAmount) + ", 带出善宝：" + String.format("%.4f", activatedSbAmount));
		
		if (bisAccount!=null) {
			bisAccount.addTrade(dateInInt, moneyAmount, status);
		}
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the type
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AccountType type) {
		this.type = type;
	}

	/**
	 * @return the activeSBAmount
	 */
	public double getActiveSBAmount() {
		return activeSBAmount;
	}

	/**
	 * @param activeSBAmount the activeSBAmount to set
	 */
	public void setActiveSBAmount(double activeSBAmount) {
		this.activeSBAmount = activeSBAmount;
	}

	/**
	 * @return the disactiveSBAmount
	 */
	public double getDisactiveSBAmount() {
		return disactiveSBAmount;
	}

	/**
	 * @param disactiveSBAmount the disactiveSBAmount to set
	 */
	public void setDisactiveSBAmount(double disactiveSBAmount) {
		this.disactiveSBAmount = disactiveSBAmount;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the postponeAmount
	 */
	public double getPostponeAmount() {
		return postponeAmount;
	}

	/**
	 * @param postponeAmount the postponeAmount to set
	 */
	public void setPostponeAmount(double postponeAmount) {
		this.postponeAmount = postponeAmount;
	}

	/**
	 * @return the upperAccount
	 */
	public Account getUpperAccount() {
		return upperAccount;
	}

	/**
	 * @param upperAccount the upperAccount to set
	 */
	public void setUpperAccount(Account upperAccount) {
		this.upperAccount = upperAccount;
	}

	/**
	 * @return the bisAccount
	 */
	public Account getBisAccount() {
		return bisAccount;
	}

	/**
	 * @param bisAccount the bisAccount to set
	 */
	public void setBisAccount(Account bisAccount) {
		this.bisAccount = bisAccount;
	}

	/**
	 * @return the dailyDetailsList
	 */
	public List<AccountDailyDetails> getDailyDetailsList() {
		return dailyDetailsList;
	}

	/**
	 * @param dailyDetailsList the dailyDetailsList to set
	 */
	public void setDailyDetailsList(List<AccountDailyDetails> dailyDetailsList) {
		this.dailyDetailsList = dailyDetailsList;
	}

	/**
	 * @return the sbDistributePool
	 */
	public SBDistributePool getSbDistributePool() {
		return sbDistributePool;
	}

	/**
	 * @param sbDistributePool the sbDistributePool to set
	 */
	public void setSbDistributePool(SBDistributePool sbDistributePool) {
		this.sbDistributePool = sbDistributePool;
	}
}
