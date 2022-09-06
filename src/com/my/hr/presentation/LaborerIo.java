package com.my.hr.presentation;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;
import com.my.hr.service.LaborerService;

public class LaborerIo {
	private LaborerService laborerService;
	private String menu;
	
	public LaborerIo(LaborerService laborerService) {
		this.laborerService = laborerService;
		this.menu = Job.labels();
	}
	
	public void play() {
		Job job = null;
		
		while( (job = choose(menu)) != Job.EXIT) {
			switch(job) {
			case LIST: listLaborers(); break;
			case ADD: addLaborer(); break;
			case FIX: fixLaborer(); break;
			case DEL: delLaborer(); 
			}
		}
	}
	
	private Job choose(String menu) {
		boolean isGood = false;
		int choice = 0;
		
		do {	
			choice = Console.inNum(menu);
			
			if(0 > choice || choice >= Job.values().length) Console.err("��Ͽ� �ش��ϴ� ��ȣ�� �Է��� �ּ���."); 
			else isGood = true;
		} while(!isGood);
		
		return Job.values()[choice];
	}
	
	private void listLaborers() {
		List<Laborer> laborers = laborerService.getLaborers();

		if(laborers.size() > 0) {
		System.out.println("ID  Name     RegDate");
		System.out.println("-----------------------");
		laborers.forEach(laborer -> Console.info(laborer));
		} else Console.info("�ٹ��ڰ� �������� �ʽ��ϴ�.");
	}
	
	private void addLaborer() {
		String name = Console.inStr("�߰��� �ٹ��ڸ��� �Է����ּ���", 5);
		
		if(!name.matches("[0-9]*")) {
			LocalDate hireDate = Console.inDate("�Ի����� �Է��ϼ���.");
			laborerService.addLaborer(name, hireDate);
			Console.info("�ٹ��ڸ� �߰��߽��ϴ�.");
		} else Console.info("�߰� �۾��� ����մϴ�.");
	}
	
	private int getLaborer(String job) {
		Laborer laborer = null;
		int laborerId = 0;
		
		do {
			laborerId = Console.inNum(job + "�� �뵿�� ID�� �Է����ּ���.");
			
			if(laborerId == 0) {
				Console.info(job + "�۾��� ����մϴ�.");
				return 0;
			}
			
			laborer = laborerService.getLaborer(laborerId);
			
			if(laborer == null) Console.err("�ش� ID�� �ٹ��ڴ� ��ȸ���� �ʽ��ϴ�.");
		} while(laborer == null);
		return laborerId;
	}
	
	private void fixLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("����");
			
			if(laborerId != 0) {
				
				int choice = 0;
				
				do {
					choice =  Console.inNum("1. �ٹ��ڸ� ����, 2. �Ի��� ����, 0. ��������");
					
					if(choice == 1) {
						String laborerName = Console.inStr("�ٹ��ڸ��� �����մϴ�.", 5);
						laborerService.fixLaborerName(laborerId ,laborerName);
						Console.info("�ش�ID�� �ٹ��ڸ��� �����Ͽ����ϴ�.");
						
					} else if(choice == 2) {
						LocalDate hireDate = Console.inDate("�Ի����� �����մϴ�.");
						laborerService.fixLaborerDate(laborerId ,hireDate);
						Console.info("�ش�ID�� �Ի����� �����Ͽ����ϴ�.");
						
					} else if(3 <= choice) Console.err("�޴���ȣ�� ��Ȯ���� �Է����ּ���.");
					
				} while(choice != 0);
				
				/*
				String name = Console.inStr("������ �̸��� �Է��ϼ���.", 5);
				LocalDate hireDate = Console.inDate("������ �Ի����� �Է��ϼ���.");
				laborerService.fixLaborer(new Laborer(laborerId, name, hireDate));
				Console.info("�����Ϸ�");
				*/
			}
		} else Console.info("������ �ٷ��ڰ� �����ϴ�.");
	}
	
	private void delLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("����");
				
			if(laborerId != 0) {
				laborerService.delLaborer(laborerId);
				Console.info("�ش� �ٹ��ڸ� �����Ͽ����ϴ�.");
			}
			
		} else Console.info("�ٷ��ڰ� �������� �ʽ��ϴ�.");	
	}
}
