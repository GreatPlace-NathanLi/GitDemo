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
		
		//��ʼ������
		
				//����ģ������
				int days = 50;
				
				int day = 20170000;
				double initMoney = 200000;
				
				//��Ͷ���
				double repeatMoney = 5000;
				//��ͶƵ��
				int ft = 1;
				//�Ʊ�ָ��
				double index = 0.3;	
				
				double myDisSbAmount = 73000;
				double bisDisSbAmount =35000;
		
		//create account
//		Account upperAccount = new Account("�˻�0", "20170501", AccountType.����ʹ��);
		
		Account bisAccount = new Account("�̼��˻�0", "20170501", AccountType.�̼�, rule);
		SBDistributePool bisSbPool = new SBDistributePool(rule);
		List<SBDistributeUnit> bisSbDistributeUnits = bisSbPool.getSBDistributeUnits();
		bisSbDistributeUnits.set(19, new SBDistributeUnit(19,5,bisDisSbAmount,false));
		bisAccount.setSbDistributePool(bisSbPool);
		
		Account myAccount = new Account("�˻�1", "20170601", AccountType.����ʹ��, rule);
//		myAccount.setUpperAccount(upperAccount);
		myAccount.setBisAccount(bisAccount);
		myAccount.setActiveSBAmount(1000.0);
		myAccount.setDisactiveSBAmount(5000.0);
		
		SBDistributePool sbPool = new SBDistributePool(rule);
		List<SBDistributeUnit> sbDistributeUnits = sbPool.getSBDistributeUnits();
		sbDistributeUnits.set(19, new SBDistributeUnit(19,5,myDisSbAmount,false));
		myAccount.setSbDistributePool(sbPool);
		
		myAccount.addTrade(day, initMoney, "��Ͷ");		
				
		
		double totalMoneyIn = initMoney;
		double myTotalMoneyOut = 0;
		double bisTotalMoneyOut = 0;
		
		for(int i = 1; i<=2*days; i++) {
			System.out.println("---------------------------------");
			
			//���㵱�շ��䡢ʣ�౦�������浱����ϸ
			//System.out.println("Day " + i + "- �Լ�0: " + sbPool);
			//System.out.println("Day " + i + "- �̼�0: " + bisSbPool);
			double sbAmount = sbPool.getTotalToDistributeSbAmount(index);
			myTotalMoneyOut+=sbAmount * 0.3;
			System.out.println("Day " + i + "- �Լ��ɼ����Ʊ���" + String.format("%.4f", sbAmount) + ", �Լ����ռ�����" + String.format("%.4f", sbAmount * 0.3));
			
			double bisSbAmount = bisSbPool.getTotalToDistributeSbAmount(index);
			bisTotalMoneyOut+=bisSbAmount * 0.3;
			System.out.println("Day " + i + "- �̼ҿɼ����Ʊ���" + String.format("%.4f", bisSbAmount) + ", �̼ҵ��ռ�����" + String.format("%.4f", bisSbAmount * 0.3));
			
			System.out.println("Day " + i + "- �����" + String.format("%.4f", totalMoneyIn) + ", �ܼ�����" + String.format("%.4f", myTotalMoneyOut + bisTotalMoneyOut) + ", �Լ��ܼ�����" + String.format("%.4f", myTotalMoneyOut) + ", �̼��ܼ�����" + String.format("%.4f", bisTotalMoneyOut));
			
			
			//������һ���Ʊ����ݣ���Ͷ��������桢���ڣ�
			//System.out.println("Day " + i + "- �Լ�1: " + sbPool);
			//System.out.println("Day " + i + "- �̼�1: " + bisSbPool);
			sbPool.rollDay();
			bisSbPool.rollDay();
			//System.out.println("Day " + i + "- �Լ�2: " + sbPool);
			//System.out.println("Day " + i + "- �̼�2: " + bisSbPool);
			//��Ͷ
			if (i%ft==0 && i<days) {
				myAccount.addTrade(day+i, repeatMoney, "��Ͷ");
				totalMoneyIn+=repeatMoney;
			}
			
		}
		
		
		
	}

}
