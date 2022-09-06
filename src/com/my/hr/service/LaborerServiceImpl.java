package com.my.hr.service;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.dao.LaborerDao;
import com.my.hr.domain.Laborer;

public class LaborerServiceImpl implements LaborerService {
	private LaborerDao laborerDao;
	
	public LaborerServiceImpl(LaborerDao laborerDao) {
		this.laborerDao = laborerDao;
	}
	
	@Override
	public List<Laborer> getLaborers() {
		return laborerDao.selectLaborers();
	}
	
	@Override
	public Laborer getLaborer(int laborerId) {
		return laborerDao.selectLaborer(laborerId);
	}
	
	@Override
	public void addLaborer(String laborerName, LocalDate hireDate) {
		laborerDao.insertLaborer(laborerName, hireDate);
	}
	
	@Override
	public void fixLaborer(Laborer laborer) {
		laborerDao.updateLaborer(laborer);
	}
	
	@Override
	public void fixLaborerName(int laborerId, String laborerName) {
		laborerDao.updateLaborerName(laborerId, laborerName);
	}
	
	@Override
	public void fixLaborerDate(int laborerId, LocalDate hireDate) {
		laborerDao.updateLaborerDate(laborerId, hireDate);
	}
	
	@Override
	public void delLaborer(int laborerId) {
		laborerDao.deleteLaborer(laborerId);
	}
}
