package com.learning.hadef.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private String code;
    private String message;
}
