package com.sera.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;

public interface CenvDegerListeService {
     public void saveKokCenvDegerListe(SeraCenvDegerListe cenvDegerListe);
     public void saveKokCenvDegerSabitler(SeraCenvSabitler cenvDegerSabit);
     
     
     public List<SeraCenvDegerListe> searchCenvDeger(String baslik);
     public SeraCenvDegerListe detayCenvDeger(long id);
     
     public List<SeraCenvDegerListe> listChildren(long id);
     public List<SeraCenvDegerListe> listDalKokCenv();
     public List<SeraCenvDegerListe> listEnAltDal();
     
     public void updateCenvDeger(SeraCenvDegerListe cenvdeger);
     public void updateCenvSabit(SeraCenvSabitler cenvsabit);
     
     public List<Long> getSeviye(long id);
     public SeraCenvDegerListe getKok();
     public SeraCenvSabitler getSabit(Long id);
     public int getYaprakQuantiy();
     public List<SeraCenvDegerListe> getParent(long id);
     
     public boolean isKokExist();
     public String checkChildType(Long parentId);
     public void deleteAllDescendant(Long id);
     
	 public JRDataSource getCenvDegerListeReport();
	 public JRDataSource listCenvDegerListe();
     
     
}
