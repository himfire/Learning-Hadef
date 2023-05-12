package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_course_resources")
@EqualsAndHashCode
public class Resource extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameAR;
    private String nameEN;
    private String descriptionAR;
    private String descriptionEN;
    private String url;
    private Long status;
}
