package com.learning.hadef.domain.dto;

import com.learning.hadef.domain.entity.Address;
import com.learning.hadef.domain.entity.Phone;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @Embedded
    private Phone phone;
    @Embedded
    private Address address;
    private String nationality;
}
