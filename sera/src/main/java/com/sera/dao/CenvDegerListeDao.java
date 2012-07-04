package com.sera.dao;

import java.util.List;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;

public interface CenvDegerListeDao {
	public void saveKokCenvDeger(SeraCenvDegerListe cenvDegerListe);
	public void saveKokCenvSabit(SeraCenvSabitler cenvDegerSabit);
	public List<SeraCenvDegerListe> listDalKokCenv();
	public List<Long> getSeviye(long id);
	public List<SeraCenvDegerListe> searchCenvDeger(String baslik);
	public SeraCenvDegerListe getCenvDeger(long id);
	public SeraCenvDegerListe getKok();
	public List<SeraCenvDegerListe> getParent(long id);
	public List<SeraCenvDegerListe> listChildren(long id);
	public void updateCenvDeger(SeraCenvDegerListe cenvdeger);
	public void updateCenvSabit(SeraCenvSabitler cenvsabit);
}
