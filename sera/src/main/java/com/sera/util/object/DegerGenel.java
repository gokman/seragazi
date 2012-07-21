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
	public void setId(Long id) {
		this.id = id;
	}

	public void setID(BigInteger val) {
		if (val == null) return;
		this.id = val.longValue();
	}
	
	public void setBASLIK(String baslik) {
		setBaslik(baslik);
	}
	
	public String getBaslik() {
		return baslik;
	}
	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public void setPARENT_ID(BigInteger val) {
		if (val == null) return;
		this.parent_id = val.longValue();
	}

	public String getParent_baslik() {
		return parent_baslik;
	}
	
	public void setPARENT_BASLIK(String val) {
		this.parent_baslik = val;
	}

	public void setParent_baslik(String parent_baslik) {
		this.parent_baslik = parent_baslik;
	}
}
