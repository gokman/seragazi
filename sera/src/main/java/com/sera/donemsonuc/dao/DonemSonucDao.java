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
  
}
