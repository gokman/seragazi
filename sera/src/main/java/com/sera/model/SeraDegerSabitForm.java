package com.sera.model;

public class SeraDegerSabitForm {

	
	private Long id; 
    private String baslik; 
    private Long parentId; 
    private String tip1; 
    private String tip2;
    private Long seviye;
    private String birim; 
    private Long sabit;
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBaslik() {
		return baslik;
	}
	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getTip1() {
		return tip1;
	}
	public void setTip1(String tip1) {
		this.tip1 = tip1;
	}
	public String getTip2() {
		return tip2;
	}
	public void setTip2(String tip2) {
		this.tip2 = tip2;
	}
	public Long getSeviye() {
		return seviye;
	}
	public void setSeviye(Long seviye) {
		this.seviye = seviye;
	}
	public String getBirim() {
		return birim;
	}
	public void setBirim(String birim) {
		this.birim = birim;
	}
	
	
	public Long getsabit() {
		return sabit;
	}
	public void setsabit(Long sabit) {
		this.sabit = sabit;
	}
}
