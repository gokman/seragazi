package com.sera.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.sera.model.SeraCenvSabitler;

public interface CenvSabitlerService {
   
	public List<SeraCenvSabitler> listSabitler();
	
	public JRDataSource getCenvSabitlerReport(); //Rapor için eklenen metod.
}
