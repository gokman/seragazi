package com.sera.util.object;

import java.math.BigInteger;

//ağaç elemanlarının id ve başlığı ile bağlı olduğu elemanın id ve başlığını tutar
public class DegerGenel {
    Long id;
    String baslik;
    Long parent_id;
    String parent_baslik;
    
    public DegerGenel(){
    	
    }
	public Long getId() {
		return id;
	}
	
	public void setID(BigInteger val) {
		if (val == null) return;
		this.id = val.longValue();
	}
	
	public String getBASLIK() {
		return baslik;
	}
	
	public void setBASLIK(String baslik) {
		this.baslik = baslik;
	}
	
	public Long getPARENT_ID() {
		return parent_id;
	}
	
	public void setPARENT_ID(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getPARENT_BASLIK() {
		return parent_baslik;
	}
	
	public void setPARENT_BASLIK(String val) {
		this.parent_baslik = val;
	}

}
