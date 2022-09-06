package com.my.hr.dao;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;

public interface LaborerDao {
	List<Laborer> selectLaborers();
	Laborer selectLaborer(int laborerId);
	void insertLaborer(String laborerName, LocalDate hireDate);
	void updateLaborer(Laborer laborer);
	void updateLaborerName(int laborerId, String laborerName);
	void updateLaborerDate(int laborerId, LocalDate hireDate);
	void deleteLaborer(int laborerId);
}
