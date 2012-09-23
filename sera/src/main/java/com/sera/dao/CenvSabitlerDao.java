package com.sera.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.sera.model.SeraCenvSabitler;
import com.sera.util.SeraCenvSabitlerRep;
import com.sera.util.object.DegerGenel;

public interface CenvSabitlerDao {
    
	public List<SeraCenvSabitler> listSabitler();
	public SeraCenvSabitler getSabit(Long id);
	
	public JRDataSource getCenvSabitlerReport(); //Report için eklenen metod.
	
	public JRDataSource getSabitlerRep(); //Report için eklenen metod.
	
}
