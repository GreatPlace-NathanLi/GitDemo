package com.shanbao;

import java.util.ArrayList;
import java.util.List;

public class SBDistributePool {

	private int levelSize;
	private int cyclicLength;
	private int toDistributeMaxLevel;
	private List<SBDistributeUnit> sbDistributeUnits;
	private SBRule rule;
		
	
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
	
	public SBDistributePool(SBRule rule) {
		this.setRule(rule);
		this.levelSize = rule.getSbDistributeLevelSize();
		this.cyclicLength = rule.getSbDistributeCyclicLength();
		this.toDistributeMaxLevel = rule.getSbToDistributeMaxLevel();
		sbDistributeUnits = new ArrayList<SBDistributeUnit>();
		for(int i = 0; i<levelSize; i++) {
			boolean flag = false;
			if (i<=toDistributeMaxLevel)
				flag = true;
			sbDistributeUnits.add(new SBDistributeUnit(i,cyclicLength,0,flag));
		}
	}
	
	//返回带出善宝数
	public double addTrade(SBTrade trade) {
		double newSbAmount = trade.getNewSbAmount();
		double remainAmount = newSbAmount;
		double tempAmount = 0;
		double activatedSbAmount = 0;
		for(int i = levelSize-1; i>0; i--) {
			tempAmount += sbDistributeUnits.get(i).getTotalSb();
			if(tempAmount>= newSbAmount) {
				activatedSbAmount = newSbAmount;
				resetUnit(sbDistributeUnits.get(i), remainAmount);
				remainAmount=0;
				break;
			} else {
				remainAmount-=sbDistributeUnits.get(i).getTotalSb();
				sbDistributeUnits.get(i).setSbAmount(0);
				sbDistributeUnits.get(i).setSbTradeList(null);
			}
		}
		if(remainAmount>0) {
			activatedSbAmount = newSbAmount - remainAmount;
		}
		trade.setActivatedSbAmount(activatedSbAmount);
		sbDistributeUnits.get(0).addSbTrade(trade);
		return activatedSbAmount;
	}
	
	private void resetUnit(SBDistributeUnit unit, double toResetAmount) {
		//System.out.println("resetUnit");
		double initSbAmount = unit.getSbAmount();
		double remainAmount = toResetAmount;
		if(initSbAmount>=toResetAmount) {
			initSbAmount-=toResetAmount;
			unit.setSbAmount(initSbAmount);
		} else {
			unit.setSbAmount(0);
			remainAmount-=initSbAmount;
			resetTrades(unit.getSbTradeList(), remainAmount);
		}
		
	}
	
	private void resetTrades(List<SBTrade> tradeList, double toResetAmount) {
		//System.out.println("resetTrades");
		double remainAmount = toResetAmount;
		for(int i=0; i<tradeList.size(); i++) {
			SBTrade trade = tradeList.get(i);
			double amount = trade.getRemainActiveSbAmount();
			if(amount>=remainAmount) {
				amount-=remainAmount;
				trade.setRemainActiveSbAmount(amount);
				break;
			} else {
				trade.setRemainActiveSbAmount(0);
				remainAmount-=amount;
			}
		}
	}
	
	public void rollDay() {
		List<SBDistributeUnit> newUnits = new ArrayList<SBDistributeUnit>();
		for(int i = 0; i<levelSize; i++) {
			newUnits.add(new SBDistributeUnit(i,cyclicLength,sbDistributeUnits.get(i).getSbAmount(),sbDistributeUnits.get(i).isDistributeFlag()));
		}
		for(SBDistributeUnit unit : sbDistributeUnits) {
			if(unit.getSbTradeList()==null)
				continue;
			
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

	/**
	 * @return the toDistributeMaxLevel
	 */
	public int getToDistributeMaxLevel() {
		return toDistributeMaxLevel;
	}

	/**
	 * @param toDistributeMaxLevel the toDistributeMaxLevel to set
	 */
	public void setToDistributeMaxLevel(int toDistributeMaxLevel) {
		this.toDistributeMaxLevel = toDistributeMaxLevel;
	}

	/**
	 * @return the rule
	 */
	public SBRule getRule() {
		return rule;
	}

	/**
	 * @param rule the rule to set
	 */
	public void setRule(SBRule rule) {
		this.rule = rule;
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
