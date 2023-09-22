package com.cft.task.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "digit_intervals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DigitInterval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "istart")
    private Integer start;

    @Column(name = "iend")
    private Integer end;
}
