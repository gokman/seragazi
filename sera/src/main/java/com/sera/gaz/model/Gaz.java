package com.sera.gaz.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "gaz")
public class Gaz  {
    
    // Raw attributes
    private Date lastUpdateDate;
    private Date createDate;
    private String name;
    private Long createdBy;
    private Long lastUpdatedBy;
    private Long id; // pk

    // ---------------------------
    // Constructors
    // ---------------------------

    public Gaz() {
    }

    public Gaz(Long primaryKey) {
        setId(primaryKey);
    }


    

    // -- [deger] ------------------------

    @Size(max = 50)
    @Column(name = "name", length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    // -- [createDate] ------------------------

    @Column(name = "CREATE_DATE", length = 20)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    //---[lastUpdatedate]-----------------
    
    @Column(name = "LAST_UPDATE_DATE", length = 20)
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
    
 // -- [lastUpdatedBy] ------------------------

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