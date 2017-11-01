package com.shanbao;

import com.shanbao.client.Account.AccountType;

public class SBRule {

	private int moneyDistributeTaxPct = 0;//税百分比0~100；
	private int sbActivatePct = 100;//暂缓善宝激活百分比0~100；
	
	private double sbIndex = 0.3;//善宝指数
	
	private int sbDistributeLevelSize = 20;
	private int sbDistributeCyclicLength = 5;
	
	private int sbToDistributeMaxLevel = 18;
		
	
	public SBRule(int moneyDistributeTaxPct, int sbActivatePct, double sbIndex, int sbDistributeLevelSize,
			int sbDistributeCyclicLength, int sbToDistributeMaxLevel) {
		super();
		this.moneyDistributeTaxPct = moneyDistributeTaxPct;
		this.sbActivatePct = sbActivatePct;
		this.setSbIndex(sbIndex);
		this.sbDistributeLevelSize = sbDistributeLevelSize;
		this.sbDistributeCyclicLength = sbDistributeCyclicLength;
		this.sbToDistributeMaxLevel = sbToDistributeMaxLevel;
	}
	
	/**
	 * @return the moneyDistributeTaxPct
	 */
	public int getMoneyDistributeTaxPct() {
		return moneyDistributeTaxPct;
	}
	/**
	 * @return the sbActivatePct
	 */
	public int getSbActivatePct() {
		return sbActivatePct;
	}
	/**
	 * @return the sbDistributeLevelSize
	 */
	public int getSbDistributeLevelSize() {
		return sbDistributeLevelSize;
	}
	/**
	 * @return the sbDistributeCyclicLength
	 */
	public int getSbDistributeCyclicLength() {
		return sbDistributeCyclicLength;
	}

	public double getSbAmountByMoneyAmount(double moneyAmount, AccountType type) {
		if(AccountType.商家.equals(type))
			return  moneyAmount/10000*300;

		return moneyAmount/10000*400;
	}

	/**
	 * @return the sbIndex
	 */
	public double getSbIndex() {
		return sbIndex;
	}

	/**
	 * @param sbIndex the sbIndex to set
	 */
	public void setSbIndex(double sbIndex) {
		this.sbIndex = sbIndex;
	}

	/**
	 * @return the sbToDistributeMaxLevel
	 */
	public int getSbToDistributeMaxLevel() {
		return sbToDistributeMaxLevel;
	}

	/**
	 * @param sbToDistributeMaxLevel the sbToDistributeMaxLevel to set
	 */
	public void setSbToDistributeMaxLevel(int sbToDistributeMaxLevel) {
		this.sbToDistributeMaxLevel = sbToDistributeMaxLevel;
	}
}
