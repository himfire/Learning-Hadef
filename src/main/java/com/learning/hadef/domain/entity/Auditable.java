package com.learning.hadef.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Auditable {

    @CreatedDate
    @Column(insertable = false,updatable = false)
    private LocalDateTime createdDate;

//    @CreatedBy
//    private String createdBy;
//
//    @LastModifiedBy
//    private String lastModifiedBy;

    @LastModifiedDate
    @Column(insertable = false,updatable = false)
    private LocalDateTime lastModifiedDate;

}
