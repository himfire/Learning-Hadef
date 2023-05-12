package com.learning.hadef.domain.entity;


import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String country;
    private String state;
    private String city;
    private String address;
}
