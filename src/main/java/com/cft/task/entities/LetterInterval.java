package com.cft.task.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "letter_intervals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LetterInterval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lstart")
    private String start;

    @Column(name = "lend")
    private String end;
}
