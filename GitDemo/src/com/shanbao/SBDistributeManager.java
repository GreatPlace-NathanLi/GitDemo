package com.shanbao;

import java.util.List;

import com.shanbao.client.Account;
import com.shanbao.client.Account.AccountType;

public class SBDistributeManager {
	
	public static void main(String[] args) {
		System.out.println("Hello");
		
		//create account
		Account upperAccount = new Account("�˻�0", "20170501", AccountType.����ʹ��);
		
		Account myAccount = new Account("�˻�1", "20170601", AccountType.����ʹ��);
		myAccount.setUpperAccount(upperAccount);
		myAccount.setActiveSBAmount(1000.0);
		myAccount.setDisactiveSBAmount(5000.0);
		
		SBDistributePool sbPool = new SBDistributePool(20, 5);
		List<SBDistributeUnit> sbDistributeUnits = sbPool.getSBDistributeUnits();
		sbDistributeUnits.set(19, new SBDistributeUnit(19,5,1000,false));
		myAccount.setSbDistributePool(sbPool);
		
		
		//create 
		//35000
		//73000
		//6800
		//3000
		//9000
		
		//��ʼ������
		
		//����ģ������
		int days = 100;
		
		int day = 20170000;
		SBTrade sbTrade = new SBTrade(day, 1000, 1000, 0, "��Ͷ");
		sbPool.getSBDistributeUnits().get(0).addSbTrade(sbTrade);
		
		
		//��ͶƵ��
		int ft = 5;
		
		for(int i = 1; i<=days; i++) {
			//���㵱�շ��䡢ʣ�౦�������浱����ϸ
			System.out.println("SBDistributePool0: " + sbPool);
			double sbAmount = sbPool.getTotalToDistributeSbAmount(0.3);
			System.out.println("Day " + i + "- �ɼ����Ʊ���" + String.format("%.4f", sbAmount) + ", distribute money��" + String.format("%.4f", sbAmount * 0.3));
			//������һ���Ʊ����ݣ���Ͷ��������桢���ڣ�
			System.out.println("SBDistributePool1: " + sbPool);
			sbPool.rollDay();
			//��Ͷ
			if (i%ft==0) {
				SBTrade sbTrade1 = new SBTrade(day+i, 10, 0, 0, "��Ͷ");
				sbPool.getSBDistributeUnits().get(0).addSbTrade(sbTrade1);
			}
			
		}
		
		
		
	}

}
