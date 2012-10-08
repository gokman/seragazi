package com.sera.donemsonuc.dao;

import java.util.List;

import com.membership.model.User;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;


public interface DonemSonucDao {

   public boolean isDonemSonucExist(String donem);
   
   public void saveDonemSonuc(SeraCenvDonemSonuc donemsonuc);
   public void updateDonemSonuc(SeraCenvDonemSonuc donemsonuc);
   
   public SeraCenvDonemSonuc getDonemSonuc(String donem,long id);
   public SeraCenvDonemSonuc getDonemSonuc(String donem,long id,String deger);
   
   public List<SeraCenvDonemSonuc> listDonemSonuc(String donem);
   public List<SeraCenvDonemSonuc> listDonemSonuc(String donem,Long id);
   
   public void deleteDonemSonuc(String donem);
  
}
