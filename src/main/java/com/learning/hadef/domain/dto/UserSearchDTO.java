package com.learning.hadef.domain.dto;

import lombok.*;

import javax.persistence.Column;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDTO {
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String idCardNo;
}
