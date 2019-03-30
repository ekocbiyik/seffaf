package com.payment.seffaf.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * enbiya on 27.03.2019
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
