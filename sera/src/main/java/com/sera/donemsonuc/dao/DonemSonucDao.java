package com.sera.donemsonuc.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.membership.model.User;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.util.DonemSonucRaporParams;


public interface DonemSonucDao {

   public boolean isDonemSonucExist(String donem);
   
   public void saveDonemSonuc(SeraCenvDonemSonuc donemsonuc);
   public void updateDonemSonuc(SeraCenvDonemSonuc donemsonuc);
   
   public SeraCenvDonemSonuc getDonemSonuc(String donem,long id);
   public SeraCenvDonemSonuc getDonemSonuc(String donem,long id,String deger);
   public JRDataSource getDonemSonucRep(DonemSonucRaporParams params);
   
   public List<SeraCenvDonemSonuc> listDonemSonuc(String donem);
   public List<SeraCenvDonemSonuc> listDonemSonuc(String donem,Long id);
   
   public void deleteDonemSonuc(String donem);
  
}
