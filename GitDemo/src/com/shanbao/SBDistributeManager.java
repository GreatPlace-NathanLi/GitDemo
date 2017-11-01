package com.shanbao;

import java.util.List;

import com.shanbao.client.Account;
import com.shanbao.client.Account.AccountType;

public class SBDistributeManager {
	
	public static void main(String[] args) {
		
		SBRule rule = new SBRule(0, 100, 0.3, 20, 5, 18);
		
		//create 
		//35000
		//73000
		//6800
		//3000
		//9000
		
		//初始化数据
		
				//设置模拟期限
				int days = 50;
				
				int day = 20170000;
				double initMoney = 200000;
				
				//复投金额
				double repeatMoney = 5000;
				//复投频率
				int ft = 1;
				//善宝指数
				double index = 0.3;	
				
				double myDisSbAmount = 73000;
				double bisDisSbAmount =35000;
		
		//create account
//		Account upperAccount = new Account("账户0", "20170501", AccountType.传递使者);
		
		Account bisAccount = new Account("商家账户0", "20170501", AccountType.商家, rule);
		SBDistributePool bisSbPool = new SBDistributePool(rule);
		List<SBDistributeUnit> bisSbDistributeUnits = bisSbPool.getSBDistributeUnits();
		bisSbDistributeUnits.set(19, new SBDistributeUnit(19,5,bisDisSbAmount,false));
		bisAccount.setSbDistributePool(bisSbPool);
		
		Account myAccount = new Account("账户1", "20170601", AccountType.传递使者, rule);
//		myAccount.setUpperAccount(upperAccount);
		myAccount.setBisAccount(bisAccount);
		myAccount.setActiveSBAmount(1000.0);
		myAccount.setDisactiveSBAmount(5000.0);
		
		SBDistributePool sbPool = new SBDistributePool(rule);
		List<SBDistributeUnit> sbDistributeUnits = sbPool.getSBDistributeUnits();
		sbDistributeUnits.set(19, new SBDistributeUnit(19,5,myDisSbAmount,false));
		myAccount.setSbDistributePool(sbPool);
		
		myAccount.addTrade(day, initMoney, "初投");		
				
		
		double totalMoneyIn = initMoney;
		double myTotalMoneyOut = 0;
		double bisTotalMoneyOut = 0;
		
		for(int i = 1; i<=2*days; i++) {
			System.out.println("---------------------------------");
			
			//计算当日分配、剩余宝数，保存当日明细
			//System.out.println("Day " + i + "- 自己0: " + sbPool);
			//System.out.println("Day " + i + "- 商家0: " + bisSbPool);
			double sbAmount = sbPool.getTotalToDistributeSbAmount(index);
			myTotalMoneyOut+=sbAmount * 0.3;
			System.out.println("Day " + i + "- 自己可激励善宝：" + String.format("%.4f", sbAmount) + ", 自己当日激励金额：" + String.format("%.4f", sbAmount * 0.3));
			
			double bisSbAmount = bisSbPool.getTotalToDistributeSbAmount(index);
			bisTotalMoneyOut+=bisSbAmount * 0.3;
			System.out.println("Day " + i + "- 商家可激励善宝：" + String.format("%.4f", bisSbAmount) + ", 商家当日激励金额：" + String.format("%.4f", bisSbAmount * 0.3));
			
			System.out.println("Day " + i + "- 总入金：" + String.format("%.4f", totalMoneyIn) + ", 总激励金额：" + String.format("%.4f", myTotalMoneyOut + bisTotalMoneyOut) + ", 自己总激励金额：" + String.format("%.4f", myTotalMoneyOut) + ", 商家总激励金额：" + String.format("%.4f", bisTotalMoneyOut));
			
			
			//计算下一日善宝数据（复投、激活、滚存、过期）
			//System.out.println("Day " + i + "- 自己1: " + sbPool);
			//System.out.println("Day " + i + "- 商家1: " + bisSbPool);
			sbPool.rollDay();
			bisSbPool.rollDay();
			//System.out.println("Day " + i + "- 自己2: " + sbPool);
			//System.out.println("Day " + i + "- 商家2: " + bisSbPool);
			//复投
			if (i%ft==0 && i<days) {
				myAccount.addTrade(day+i, repeatMoney, "复投");
				totalMoneyIn+=repeatMoney;
			}
			
		}
		
		
		
	}

}
