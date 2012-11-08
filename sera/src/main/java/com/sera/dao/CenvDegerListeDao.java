package com.sera.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;

public interface CenvDegerListeDao {
	
	public void saveKokCenvDeger(SeraCenvDegerListe cenvDegerListe);
	public void saveKokCenvSabit(SeraCenvSabitler cenvDegerSabit);
	
	public void updateCenvDeger(SeraCenvDegerListe cenvdeger);
	public void updateCenvSabit(SeraCenvSabitler cenvsabit);
	
	public void deleteCenvDeger(SeraCenvDegerListe cenvdeger);
	
	public List<SeraCenvDegerListe> listDalKokCenv();
	public List<SeraCenvDegerListe> listChildren(long id);
	public List<SeraCenvDegerListe> listyaprak();
	public List<SeraCenvDegerListe> listyaprak(String tip);
	public List<SeraCenvDegerListe> listDescendant(Long id);
	public List<SeraCenvDegerListe> ListEnAltDal();
	public List<SeraCenvDegerListe> listTumYapi();
	
	public SeraCenvSabitler getCenvSabit(long id);
	public List<SeraCenvDegerListe> getAncestors(Long id);
	public List<SeraCenvDegerListe> getParent(long id);
	public SeraCenvDegerListe getCenvDeger(long id);
	public SeraCenvDegerListe getKok();
	public List<Long> getSeviye(long id);
	public int getYaprakQuantity();
	public int getYaprakQuantity(String tip);
	
	public List<SeraCenvDegerListe> searchCenvDeger(String baslik);
	
	public boolean isKokExist();	

	public JRDataSource getCenvDegerListeReport();
	
}