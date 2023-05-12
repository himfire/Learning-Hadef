package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_experience")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startDate;
    private String endDate;
    private boolean isPresent;
    private String jobTitle;
    private String jobDescription;
}