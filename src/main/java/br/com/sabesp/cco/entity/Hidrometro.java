package br.com.sabesp.cco.entity;

import br.com.sabesp.cco.entity.enums.SolicitanteEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_HIDROMETRO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hidrometro {

    @Id
    @GeneratedValue(generator = "HIDROMETRO_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="HIDROMETRO_SEQ", sequenceName = "HIDROMETRO_SEQ", allocationSize=1)
    @Column(name = "ID_HIDROMETRO")
    private Long id;

    @Column(name = "NU_SERIE", nullable = false)
    private String nuSerie;

    @Column(name = "TOTAL_M3", nullable = false)
    private double totalM3;

    @Column(name = "PDE", nullable = false)
    private String pde;

    @Column(name = "OS_CONECTA", nullable = false)
    private String osConecta;

    @Enumerated(EnumType.STRING)
    @Column(name = "SOLICITANTE", nullable = false)
    private SolicitanteEnum solicitante;

    @Column(name = "CLASSE", nullable = false)
    private String classe;
}
