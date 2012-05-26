package com.sera.dao;

import java.util.List;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;

public interface CenvDegerListeDao {
	public void saveCenvDeger(SeraCenvDegerListe cenvDegerListe);
	public List<SeraCenvDegerListe> listcenv();
}
