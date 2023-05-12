package com.learning.hadef.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private Long count;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long status;
}
