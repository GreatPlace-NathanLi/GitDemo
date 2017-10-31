package com.shanbao;

public class SBRule {

	private int moneyDistributeTaxPct = 0;//˰�ٷֱ�0~100��
	private int sbActivatePct = 100;//�ݻ��Ʊ�����ٷֱ�0~100��
	
	private int sbDistributeLevelSize = 20;
	private int sbDistributeCyclicLength = 5;
		
	
	public SBRule(int moneyDistributeTaxPct, int sbActivatePct, int sbDistributeLevelSize,
			int sbDistributeCyclicLength) {
		super();
		this.moneyDistributeTaxPct = moneyDistributeTaxPct;
		this.sbActivatePct = sbActivatePct;
		this.sbDistributeLevelSize = sbDistributeLevelSize;
		this.sbDistributeCyclicLength = sbDistributeCyclicLength;
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

	public double getSbAmountByMoneyIn(double moneyAmount) {
		return moneyAmount/10000*700;
	}
}
