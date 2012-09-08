package com.sera.util;

public class HesaplamaKutucuk {
     public String aciklama;
     public Long id;
     //kutuda tip alanı da gerekli.
     //çünkü veritabanına id leri kaydedeceğiz. id olunca ben bunun yaprak mı faktör mü olduğunu anlayamam
     public String tip;
     
     
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public HesaplamaKutucuk(String tip,String aciklama, Long id) {
		super();
		this.aciklama = aciklama;
		this.id = id;
		this.tip=tip;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
