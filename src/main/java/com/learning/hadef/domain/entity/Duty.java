package com.learning.hadef.domain.entity;

import jdk.jfr.Timestamp;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Duty extends Auditable{

    private String name;
    @Timestamp
    private LocalTime startTime;
    @Timestamp
    private LocalTime endTime;
}
