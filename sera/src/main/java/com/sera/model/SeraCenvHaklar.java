/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/domain/Entity.e.vm.java
 */
package com.sera.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;

@Entity
@Table(name = "sera_cenv_haklar")
public class SeraCenvHaklar  {
   
    // Raw attributes
    private Long userId;
    private Long baslikId;
    private Date creationDate;
    private Long createdBy;
    private Date lastUpdateDate;
    private Long lastUpdateBy;
    private Long altDal;
    private String baslik;
    private Long id; // pk

    // -------------------------------
    // Getter & Setter
    // -------------------------------

    // -- [userId] ------------------------

    @Column(name = "USER_ID", precision = 19)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // -- [baslikId] ------------------------

    @Column(name = "BASLIK_ID", precision = 19)
    public Long getBaslikId() {
        return baslikId;
    }

    public void setBaslikId(Long baslikId) {
        this.baslikId = baslikId;
    }

    // -- [creationDate] ------------------------

    @Column(name = "CREATION_DATE", length = 10)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    // -- [createdBy] ------------------------

    @Column(name = "CREATED_BY", precision = 19)
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    // -- [lastUpdateDate] ------------------------

    @Column(name = "LAST_UPDATE_DATE", length = 10)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    // -- [lastUpdateBy] ------------------------

    @Column(name = "LAST_UPDATE_BY", precision = 19)
    public Long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    // -- [altDal] ------------------------

    @Column(name = "ALT_DAL", precision = 19)
    public Long getAltDal() {
        return altDal;
    }

    public void setAltDal(Long altDal) {
        this.altDal = altDal;
    }

    // -- [baslik] ------------------------

    @Size(max = 64)
    @Column(name = "BASLIK", length = 64)
    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    // -- [id] ------------------------

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