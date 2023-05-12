package com.learning.hadef.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_faq")
@EqualsAndHashCode
public class FAQ extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleAR;
    private String titleEN;
    private String answerAR;
    private String answerEN;
}
