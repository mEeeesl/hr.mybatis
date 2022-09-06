package com.my.hr.domain;

import java.time.LocalDate;

public class Laborer {
	private int laborerId;	// 구별하는 Key ( 따로 지정해두는게 좋다함 ) id는 중복이 안되기에 key로 두기좋음
	private String name;
	private LocalDate hireDate;
	
	public Laborer(int laborerId, String name, LocalDate hireDate) {
		this.laborerId = laborerId;
		this.name = name;
		this.hireDate = hireDate;
	}

	public int getLaborerId() {
		return laborerId;
	}
	public String getName() {
		return name;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setLaborerId(int laborerId) {
		this.laborerId = laborerId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return String.format("%2d  %-5s %s", laborerId, name, hireDate);
	}
}
