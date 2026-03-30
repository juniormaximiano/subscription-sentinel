package model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nomeServico;

    private BigDecimal valor;
    private LocalDate dataInicio;
    private LocalDate dataRenovacao;
    @Enumerated(EnumType.STRING)
    private TipoPeriodicidade periodicidade;

    private boolean ativo;

}
