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
@Table(name = "sera_cenv_hesaplama")
public class SeraCenvHesaplama  {
    
    // Raw attributes
    private Long parentId;
    private String hesaplama;
    private Date creationDate;
    private String createdBy;
    private String detay;
    private Long id; // pk

   
    // -- [parentId] ------------------------

    @Column(name = "PARENT_ID", precision = 19)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    // -- [hesaplama] ------------------------

    @Size(max = 150)
    @Column(name = "HESAPLAMA", length = 150)
    public String getHesaplama() {
        return hesaplama;
    }

    public void setHesaplama(String hesaplama) {
        this.hesaplama = hesaplama;
    }

    // -- [creationDate] ------------------------

    @Column(name = "CREATION_DATE", length = 30)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    // -- [createdBy] ------------------------

    @Column(name = "CREATED_BY")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // -- [detay] ------------------------

    @Size(max = 64)
    @Column(name = "DETAY", length = 64)
    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
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