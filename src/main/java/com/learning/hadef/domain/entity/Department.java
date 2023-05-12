package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "tbl_department")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameAr;
    private String nameEn;
    private String descriptionAr;
    private String descriptionEn;
}
