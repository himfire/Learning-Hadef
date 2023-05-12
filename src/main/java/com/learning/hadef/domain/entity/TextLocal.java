package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_text_local")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TextLocal {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Lob
    private String textBox;
    @OneToOne
    private Language lang;
}
