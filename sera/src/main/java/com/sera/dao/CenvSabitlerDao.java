package com.sera.dao;

import java.util.List;

import com.sera.model.SeraCenvSabitler;

public interface CenvSabitlerDao {
    
	public List<SeraCenvSabitler> listSabitler();
	public SeraCenvSabitler getSabit(Long id);
}
