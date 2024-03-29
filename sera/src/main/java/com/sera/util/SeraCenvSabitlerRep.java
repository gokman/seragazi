/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/domain/Entity.e.vm.java
 */
package com.sera.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;


public class SeraCenvSabitlerRep implements java.io.Serializable{
        // Raw attributes
    private String baslik; 
    private Double sabit;  
    private Date createDate;

   	
    public Date getcreateDate() {
		return createDate;
	}
	public void setcreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }



    
    public Double getsabit() {
        return sabit;
    }

    public void setsabit(Double sabit) {
        this.sabit = sabit;
    }

   
}