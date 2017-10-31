package com.shanbao.client;

import java.util.Date;

public class AccountDailyDetails {

	private Date date;
	
	private double sbAdd;
	private double sbReduce;
	private double sbActivate;
	private double sbFreeze;
	
	private int sbDistributeMaxLevel;
	private double sbDistributeAmount;
	private double moneyDistributeAmount;
	
	private MoneyIn moneyIn;
	private MoneyOut moneyOut;
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the sbAdd
	 */
	public double getSbAdd() {
		return sbAdd;
	}
	/**
	 * @param sbAdd the sbAdd to set
	 */
	public void setSbAdd(double sbAdd) {
		this.sbAdd = sbAdd;
	}
	/**
	 * @return the sbReduce
	 */
	public double getSbReduce() {
		return sbReduce;
	}
	/**
	 * @param sbReduce the sbReduce to set
	 */
	public void setSbReduce(double sbReduce) {
		this.sbReduce = sbReduce;
	}
	/**
	 * @return the sbActivate
	 */
	public double getSbActivate() {
		return sbActivate;
	}
	/**
	 * @param sbActivate the sbActivate to set
	 */
	public void setSbActivate(double sbActivate) {
		this.sbActivate = sbActivate;
	}
	/**
	 * @return the sbFreeze
	 */
	public double getSbFreeze() {
		return sbFreeze;
	}
	/**
	 * @param sbFreeze the sbFreeze to set
	 */
	public void setSbFreeze(double sbFreeze) {
		this.sbFreeze = sbFreeze;
	}
	/**
	 * @return the sbDistributeMaxLevel
	 */
	public int getSbDistributeMaxLevel() {
		return sbDistributeMaxLevel;
	}
	/**
	 * @param sbDistributeMaxLevel the sbDistributeMaxLevel to set
	 */
	public void setSbDistributeMaxLevel(int sbDistributeMaxLevel) {
		this.sbDistributeMaxLevel = sbDistributeMaxLevel;
	}
	/**
	 * @return the sbDistributeAmount
	 */
	public double getSbDistributeAmount() {
		return sbDistributeAmount;
	}
	/**
	 * @param sbDistributeAmount the sbDistributeAmount to set
	 */
	public void setSbDistributeAmount(double sbDistributeAmount) {
		this.sbDistributeAmount = sbDistributeAmount;
	}
	/**
	 * @return the moneyDistributeAmount
	 */
	public double getMoneyDistributeAmount() {
		return moneyDistributeAmount;
	}
	/**
	 * @param moneyDistributeAmount the moneyDistributeAmount to set
	 */
	public void setMoneyDistributeAmount(double moneyDistributeAmount) {
		this.moneyDistributeAmount = moneyDistributeAmount;
	}
	/**
	 * @return the moneyIn
	 */
	public MoneyIn getMoneyIn() {
		return moneyIn;
	}
	/**
	 * @param moneyIn the moneyIn to set
	 */
	public void setMoneyIn(MoneyIn moneyIn) {
		this.moneyIn = moneyIn;
	}
	/**
	 * @return the moneyOut
	 */
	public MoneyOut getMoneyOut() {
		return moneyOut;
	}
	/**
	 * @param moneyOut the moneyOut to set
	 */
	public void setMoneyOut(MoneyOut moneyOut) {
		this.moneyOut = moneyOut;
	}
}
