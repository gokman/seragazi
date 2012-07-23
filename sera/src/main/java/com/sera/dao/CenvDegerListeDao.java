package com.sera.dao;

import java.util.List;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;

public interface CenvDegerListeDao {
	public void saveKokCenvDeger(SeraCenvDegerListe cenvDegerListe);
	public void saveKokCenvSabit(SeraCenvSabitler cenvDegerSabit);
	
	public void updateCenvDeger(SeraCenvDegerListe cenvdeger);
	public void updateCenvSabit(SeraCenvSabitler cenvsabit);
	
	public List<SeraCenvDegerListe> listDalKokCenv();
	public List<SeraCenvDegerListe> listChildren(long id);
	
	public SeraCenvSabitler getCenvSabit(long id);
	public List<SeraCenvDegerListe> getAncestors(Long id);
	public List<SeraCenvDegerListe> getParent(long id);
	public SeraCenvDegerListe getCenvDeger(long id);
	public SeraCenvDegerListe getKok();
	public List<Long> getSeviye(long id);
	public int getYaprakQuantity();
	public int getYaprakQuantity(String tip);
	public List<SeraCenvDegerListe> searchCenvDeger(String baslik);
	public List<SeraCenvDegerListe> listyaprak();
	public List<SeraCenvDegerListe> listyaprak(String tip);
	
	public boolean isKokExist();
	
}
