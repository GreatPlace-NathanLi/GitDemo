package com.shanbao;

public class SBTrade {

	private int tradeDate;
	
	private double newSbAmount;
	
	private double activatedSbAmount;
	
	private int dayCount;
	
	private String type;
	
	private double remainActiveSbAmount;

	public SBTrade(int tradeDate, double newSbAmount, double activatedSbAmount, int dayCount, String type) {
		super();
		this.tradeDate = tradeDate;
		this.newSbAmount = newSbAmount;
		this.activatedSbAmount = activatedSbAmount;
		this.dayCount = dayCount;
		this.type = type;
		this.remainActiveSbAmount = newSbAmount + activatedSbAmount;
	}
	
	public void rollDay() {
		dayCount++;
	}

	/**
	 * @return the tradeDate
	 */
	public int getTradeDate() {
		return tradeDate;
	}

	/**
	 * @param tradeDate the tradeDate to set
	 */
	public void setTradeDate(int tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * @return the newSbAmount
	 */
	public double getNewSbAmount() {
		return newSbAmount;
	}

	/**
	 * @param newSbAmount the newSbAmount to set
	 */
	public void setNewSbAmount(double newSbAmount) {
		this.newSbAmount = newSbAmount;
	}

	/**
	 * @return the activatedSbAmount
	 */
	public double getActivatedSbAmount() {
		return activatedSbAmount;
	}

	/**
	 * @param activatedSbAmount the activatedSbAmount to set
	 */
	public void setActivatedSbAmount(double activatedSbAmount) {
		this.activatedSbAmount = activatedSbAmount;
	}

	/**
	 * @return the dayCount
	 */
	public int getDayCount() {
		return dayCount;
	}

	/**
	 * @param dayCount the dayCount to set
	 */
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the remainActiveSbAmount
	 */
	public double getRemainActiveSbAmount() {
		return remainActiveSbAmount;
	}

	/**
	 * @param remainActiveSbAmount the remainActiveSbAmount to set
	 */
	public void setRemainActiveSbAmount(double remainActiveSbAmount) {
		this.remainActiveSbAmount = remainActiveSbAmount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SBTrade [tradeDate=" + tradeDate + ", newSbAmount=" + newSbAmount + ", activatedSbAmount="
				+ activatedSbAmount + ", dayCount=" + dayCount + ", type=" + type + ", remainActiveSbAmount="
				+ remainActiveSbAmount + "]";
	}

}
