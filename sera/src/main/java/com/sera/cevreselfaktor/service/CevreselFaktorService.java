package com.sera.cevreselfaktor.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;


import com.membership.model.User;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;

public interface CevreselFaktorService {
    
    public void saveCevreselFaktor(SeraCevreselFaktor cevreselFaktor);
	
	public void updateCevreselFaktor(SeraCevreselFaktor cevreselFaktor);
	
	public void deleteCevreselFaktor(SeraCevreselFaktor cevreselFaktor);
	
	public List<SeraCevreselFaktor> listCevreselFaktor();
	
	public SeraCevreselFaktor getCevreselFaktor(long id);
	public JRDataSource getCevreselFaktorReport();

	}