package com.shanbao;

import java.util.List;

import com.shanbao.client.Account;
import com.shanbao.client.Account.AccountType;

public class SBDistributeManager {
	
	public static void main(String[] args) {
		
		SBRule rule = new SBRule(0, 100, 0.3, 20, 5, 18);
		
		//create account
//		Account upperAccount = new Account("账户0", "20170501", AccountType.传递使者);
		
		Account bisAccount = new Account("商家账户0", "20170501", AccountType.商家, rule);
		SBDistributePool bisSbPool = new SBDistributePool(rule);
		List<SBDistributeUnit> bisSbDistributeUnits = bisSbPool.getSBDistributeUnits();
		bisSbDistributeUnits.set(19, new SBDistributeUnit(19,5,0,false));
		bisAccount.setSbDistributePool(bisSbPool);
		
		Account myAccount = new Account("账户1", "20170601", AccountType.传递使者, rule);
//		myAccount.setUpperAccount(upperAccount);
		myAccount.setBisAccount(bisAccount);
		myAccount.setActiveSBAmount(1000.0);
		myAccount.setDisactiveSBAmount(5000.0);
		
		SBDistributePool sbPool = new SBDistributePool(rule);
		List<SBDistributeUnit> sbDistributeUnits = sbPool.getSBDistributeUnits();
		sbDistributeUnits.set(19, new SBDistributeUnit(19,5,1000,false));
		myAccount.setSbDistributePool(sbPool);
		
		
		
		//create 
		//35000
		//73000
		//6800
		//3000
		//9000
		
		//初始化数据
		
		//设置模拟期限
		int days = 100;
		
		int day = 20170000;
		myAccount.addTrade(day, 10000, "初投");		
		
		//复投频率
		int ft = 5;
		
		for(int i = 1; i<=days; i++) {
			//计算当日分配、剩余宝数，保存当日明细
			System.out.println("SBDistributePool0: " + sbPool);
			double sbAmount = sbPool.getTotalToDistributeSbAmount(0.3);
			System.out.println("Day " + i + "- 可激励善宝：" + String.format("%.4f", sbAmount) + ", distribute money：" + String.format("%.4f", sbAmount * 0.3));
			//计算下一日善宝数据（复投、激活、滚存、过期）
			System.out.println("SBDistributePool1: " + sbPool);
			sbPool.rollDay();
			//复投
			if (i%ft==0) {
				myAccount.addTrade(day+i, 10000, "复投");
			}
			
		}
		
		
		
	}

}
