package br.com.sabesp.cco.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CCO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cco {

    @Id
    @GeneratedValue(generator = "CCO_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="CCO_SEQ", sequenceName = "CCO_SEQ", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    private LocalDateTime data;

    private double vazao;
}
