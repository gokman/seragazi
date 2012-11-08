package com.sera.cevreselfaktor.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.sera.cevreselfaktor.model.SeraCevreselFaktor;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;

public interface CevreselFaktorDao {
	
	public void saveCevreselFaktor(SeraCevreselFaktor cevreselFaktor);
	
	public void updateCevreselFaktor(SeraCevreselFaktor cevreselFaktor);
	
	public void deleteCevreselFaktor(SeraCevreselFaktor cevreselFaktor);
	
	public List<SeraCevreselFaktor> listCevreselFaktor();
	
	public SeraCevreselFaktor getCevreselFaktor(long id);
	public JRDataSource getCevreselFaktorReport();
	
	
}