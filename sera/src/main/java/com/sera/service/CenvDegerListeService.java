package com.sera.service;

import java.util.List;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;

public interface CenvDegerListeService {
     public void saveCenvDegerListe(SeraCenvDegerListe cenvDegerListe);
     public List<SeraCenvDegerListe> listcenv();
}
