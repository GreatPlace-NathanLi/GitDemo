package com.shanbao;

import java.util.ArrayList;
import java.util.List;

public class SBDistributePool {

	private int levelSize;
	private int cyclicLength;
	private List<SBDistributeUnit> sbDistributeUnits;
		
	
	public SBDistributePool(int levelSize, int cyclicLength, List<SBDistributeUnit> sbDistributeUnits) {
		super();
		this.levelSize = levelSize;
		this.cyclicLength = cyclicLength;
		this.sbDistributeUnits = sbDistributeUnits;
	}
	
	public SBDistributePool(int levelSize, int cyclicLength) {
		this(levelSize, cyclicLength, null);
		sbDistributeUnits = new ArrayList<SBDistributeUnit>();
		for(int i = 0; i<levelSize; i++) {
			sbDistributeUnits.add(new SBDistributeUnit(i,cyclicLength,0,true));
		}
	}
	
	public void rollDay() {
		List<SBDistributeUnit> newUnits = new ArrayList<SBDistributeUnit>();
		for(int i = 0; i<levelSize; i++) {
			newUnits.add(new SBDistributeUnit(i,cyclicLength,0,sbDistributeUnits.get(i).isDistributeFlag()));
		}
		for(SBDistributeUnit unit : sbDistributeUnits) {
			for(SBTrade trade : unit.getSbTradeList()) {
				int index = trade.getDayCount()/cyclicLength;
				if (index<levelSize) {
					newUnits.get(index).addSbTrade(trade);
				}
				trade.rollDay();
			}
		}
		sbDistributeUnits = newUnits;
	}
	
	public double getTotalToDistributeSbAmount() {
		double amount = 0;
		for(SBDistributeUnit unit : sbDistributeUnits) {
			amount+=unit.getTotalToDistributeSb();
		}
		return amount;
	}
	
	public double getTotalToDistributeSbAmount(double x) {
		double amount = 0;
		for(SBDistributeUnit unit : sbDistributeUnits) {
			amount+=unit.getTotalToDistributeSb(x);
		}
		return amount;
	}
	
	/**
	 * @return the levelSize
	 */
	public int getLevelSize() {
		return levelSize;
	}
	/**
	 * @return the cyclicLength
	 */
	public int getCyclicLength() {
		return cyclicLength;
	}
	/**
	 * @return the sBDistributeUnits
	 */
	public List<SBDistributeUnit> getSBDistributeUnits() {
		return sbDistributeUnits;
	}
	/**
	 * @param levelSize the levelSize to set
	 */
	public void setLevelSize(int levelSize) {
		this.levelSize = levelSize;
	}
	/**
	 * @param cyclicLength the cyclicLength to set
	 */
	public void setCyclicLength(int cyclicLength) {
		this.cyclicLength = cyclicLength;
	}
	/**
	 * @param sBDistributeUnits the sBDistributeUnits to set
	 */
	public void setSBDistributeUnits(List<SBDistributeUnit> sBDistributeUnits) {
		sbDistributeUnits = sBDistributeUnits;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SBDistributePool [levelSize=" + levelSize + ", cyclicLength=" + cyclicLength + ", sbDistributeUnits="
				+ sbDistributeUnits + "]";
	}
}
