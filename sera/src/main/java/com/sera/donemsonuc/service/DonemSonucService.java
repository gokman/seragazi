package com.sera.donemsonuc.service;

import java.util.List;


import com.membership.model.User;

public interface DonemSonucService {
    
	//o döneme ait tüm girişler tam mı onu kontrol edeceğiz
	public boolean isControlGirisFull(String donem);
	}