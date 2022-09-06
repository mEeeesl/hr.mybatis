package com.my.hr.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public interface Console {
	Scanner sc = new Scanner(System.in);
	
	static void info(Object obj) {
		System.out.println(obj);
	}
	
	static void err(String msg) {
		System.out.println("ERROR] " + msg);
	}
	
	private static void in(String msg) {
		System.out.println(msg + "\n> ");
	}
	
	static String inStr(String msg, int len) {
		boolean isGood = false;
		String line = "";
		
		do {
			in(msg);
			
			line = sc.nextLine().trim();
			isGood = 0 < line.length() && line.length() <= len;
			
			if(!isGood) err("1�� �̻� " + len + "�� ���Ϸ� �Է����ּ���.");
		} while(!isGood);
		
		return line;
	}
	
	public static int inNum(String msg) { // �뵿�� ã�� + �޴���ȣ
		boolean isGood = false;
		String line = "";
		int num = 0;
		
		do {
			in(msg);
			
			line = sc.nextLine();
												// �� ���ڸ�
			if(line.length() > 0 && line.matches("[0-9]*")) {
				
				try {
					num = Integer.parseInt(line);
					isGood = true;
				} catch(Exception e) {}
				
			} else err("���Է� ���ּ���.");
		} while(!isGood);
		
		return num;
	}
	
	static LocalDate inDate(String msg) {
		String line = "";
		LocalDate date = null;
		
		do {
			in(msg);
			line = sc.nextLine().trim();
			
			if(line.equals("0")) break;
			
			if(line.length() > 0) {
				try {
					date = LocalDate.parse(line, DateTimeFormatter.ISO_DATE);
				} catch(Exception e) {}
			}
			if(date == null) err("���� ��¥�� 'YYYY-MM-DD �������� �Է����ּ���.");
		} while(date == null);
		
		return date;
	}
}
