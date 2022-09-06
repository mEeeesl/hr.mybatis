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
			
			if(0 > choice || choice >= Job.values().length) Console.err("목록에 해당하는 번호를 입력해 주세요."); 
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
		} else Console.info("근무자가 존재하지 않습니다.");
	}
	
	private void addLaborer() {
		String name = Console.inStr("추가할 근무자명을 입력해주세요", 5);
		
		if(!name.matches("[0-9]*")) {
			LocalDate hireDate = Console.inDate("입사일을 입력하세요.");
			laborerService.addLaborer(name, hireDate);
			Console.info("근무자를 추가했습니다.");
		} else Console.info("추가 작업을 취소합니다.");
	}
	
	private int getLaborer(String job) {
		Laborer laborer = null;
		int laborerId = 0;
		
		do {
			laborerId = Console.inNum(job + "할 노동자 ID를 입력해주세요.");
			
			if(laborerId == 0) {
				Console.info(job + "작업을 취소합니다.");
				return 0;
			}
			
			laborer = laborerService.getLaborer(laborerId);
			
			if(laborer == null) Console.err("해당 ID의 근무자는 조회되지 않습니다.");
		} while(laborer == null);
		return laborerId;
	}
	
	private void fixLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("수정");
			
			if(laborerId != 0) {
				
				int choice = 0;
				
				do {
					choice =  Console.inNum("1. 근무자명 수정, 2. 입사일 수정, 0. 수정종료");
					
					if(choice == 1) {
						String laborerName = Console.inStr("근무자명을 수정합니다.", 5);
						laborerService.fixLaborerName(laborerId ,laborerName);
						Console.info("해당ID의 근무자명을 수정하였습니다.");
						
					} else if(choice == 2) {
						LocalDate hireDate = Console.inDate("입사일을 수정합니다.");
						laborerService.fixLaborerDate(laborerId ,hireDate);
						Console.info("해당ID의 입사일을 수정하였습니다.");
						
					} else if(3 <= choice) Console.err("메뉴번호를 재확인후 입력해주세요.");
					
				} while(choice != 0);
				
				/*
				String name = Console.inStr("수정할 이름을 입력하세요.", 5);
				LocalDate hireDate = Console.inDate("수정할 입사일을 입력하세요.");
				laborerService.fixLaborer(new Laborer(laborerId, name, hireDate));
				Console.info("수정완료");
				*/
			}
		} else Console.info("수정할 근로자가 없습니다.");
	}
	
	private void delLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("삭제");
				
			if(laborerId != 0) {
				laborerService.delLaborer(laborerId);
				Console.info("해당 근무자를 삭제하였습니다.");
			}
			
		} else Console.info("근로자가 존재하지 않습니다.");	
	}
}
