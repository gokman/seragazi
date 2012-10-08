package com.sera.yetki.model;



import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sera_cenv_yetkiler")
public class SeraCenvYetkiler implements java.io.Serializable{
    
    
    private Long id; // pk
    private Long userId;
    private Long baslikId;
    private Date createDate;
    private Long createdBy;
    

	public SeraCenvYetkiler() {
    }
    
    @Column(name = "user_id")
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "baslik_id")
	public Long getBaslikId() {
		return baslikId;
	}

	public void setBaslikId(Long baslikId) {
		this.baslikId = baslikId;
	}


	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Column(name = "created_by")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


    @Column(name = "ID", precision = 20)
    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
}