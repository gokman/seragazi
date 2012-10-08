package com.sera.yetki.dao;

import java.util.List;

import com.membership.model.User;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;


public interface YetkiDao {

   public void saveYetki(Long userId,List<Long> id,Long loggedId);
   public void saveYetki(Long userId,Long id,Long loggedId);
   public void deleteYetki(Long userId,List<Long> id);
   public void deleteYetki(Long userId,Long id);
   //o kullanıcının o başlık a ait yetkisi var mı onu denetleyen yöntemdir
   public boolean controlYetkiVarMi(Long userId,Long id);
  
}
