package com.shanbao;

import java.util.LinkedList;
import java.util.List;

public class SBDistributeUnit {

	private int level;
	private int cyclicLength;
	private double sbAmount;
	private boolean distributeFlag;
	
	private List<SBTrade> sbTradeList;
	
	public SBDistributeUnit() {
		sbTradeList = new LinkedList<SBTrade>();
	}
	
	public SBDistributeUnit(int level, int cyclicLength, int sbAmount, boolean distributeFlag) {
		this();
		this.level = level;
		this.cyclicLength = cyclicLength;
		this.sbAmount = sbAmount;
		this.distributeFlag = distributeFlag;
	}
	
	public double getTotalToDistributeSb() {
		if (distributeFlag==true && sbTradeList!=null && sbTradeList.size()>0) {
			double toDistributeAmount = 0;
			for(SBTrade trade : sbTradeList) {
				toDistributeAmount+=trade.getRemainActiveSbAmount();
			}
			return toDistributeAmount;
		} else {
			return 0;
		}
	}
	
	public double getTotalToDistributeSb(double x) {
		if (distributeFlag==true && sbTradeList!=null && sbTradeList.size()>0) {
			double toDistributeAmount = 0;
			for(SBTrade trade : sbTradeList) {
				double tempAmount = trade.getRemainActiveSbAmount();
				toDistributeAmount+=tempAmount;
				trade.setRemainActiveSbAmount(tempAmount*(1-x/100));
				
			}
			return toDistributeAmount;
		} else {
			return 0;
		}
	}
	
	public void addSbTrade(SBTrade sbTrade) {
		sbTradeList.add(sbTrade);
	}
	
	public void removeSbTrade(SBTrade sbTrade) {
		sbTradeList.remove(sbTrade);
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @return the cyclicLength
	 */
	public int getCyclicLength() {
		return cyclicLength;
	}
	/**
	 * @return the sbAmount
	 */
	public double getSbAmount() {
		return sbAmount;
	}
	/**
	 * @return the distributeFlag
	 */
	public boolean isDistributeFlag() {
		return distributeFlag;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @param cyclicLength the cyclicLength to set
	 */
	public void setCyclicLength(int cyclicLength) {
		this.cyclicLength = cyclicLength;
	}
	/**
	 * @param sbAmount the sbAmount to set
	 */
	public void setSbAmount(double sbAmount) {
		this.sbAmount = sbAmount;
	}
	/**
	 * @param distributeFlag the distributeFlag to set
	 */
	public void setDistributeFlag(boolean distributeFlag) {
		this.distributeFlag = distributeFlag;
	}

	/**
	 * @return the sbTradeList
	 */
	public List<SBTrade> getSbTradeList() {
		return sbTradeList;
	}

	/**
	 * @param sbTradeList the sbTradeList to set
	 */
	public void setSbTradeList(List<SBTrade> sbTradeList) {
		this.sbTradeList = sbTradeList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SBDistributeUnit [level=" + level + ", cyclicLength=" + cyclicLength + ", sbAmount=" + sbAmount
				+ ", distributeFlag=" + distributeFlag + ", sbTradeList=" + sbTradeList + "]";
	}


}
