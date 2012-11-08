package com.sera.cevreselfaktor.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sera_cevresel_faktor")
public class SeraCevreselFaktor  {
    
    // Raw attributes
    private Date lastUpdateDate;
    private String aciklama;
    private Date createDate;
    private Long createdBy;
    private Long lastUpdatedBy;
    private Long id; // pk
    private Double deger;

    // ---------------------------
    // Constructors
    // ---------------------------

    public SeraCevreselFaktor() {
    }

    public SeraCevreselFaktor(Long primaryKey) {
        setId(primaryKey);
    }


    

    // -- [baslik] ------------------------

    @Size(max = 64)
    @Column(name = "aciklama", length = 64)
    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    // -- [deger] ------------------------

    @Column(name = "DEGER", precision = 19)
    public Double getDeger() {
        return deger;
    }

    public void setDeger(Double deger) {
        this.deger = deger;
    }

    // -- [createDate] ------------------------

    @Column(name = "CREATE_DATE", length = 10)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    //---[lastUpdatedate]-----------------
    
    @Column(name = "LAST_UPDATE_DATE", length = 10)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    

    // -- [createdBy] ------------------------

    @Column(name = "CREATED_BY", precision = 19)
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    
    
    @Column(name = "LAST_UPDATED_BY", precision = 19)
    public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}


    // -- [id] ------------------------

    @NotNull
    @Column(name = "ID", precision = 19)
    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


 

   
}