package com.sb.subscriptionSentinel.model;

import com.sb.subscriptionSentinel.dto.AssinaturaDTO;
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

    public Assinatura() {
    }

    public Assinatura(AssinaturaDTO dados) {

        this.nomeServico =  dados.nomePlano();
        this.valor = dados.valor();
        this.periodicidade = dados.tipo();
        this.dataInicio = dados.dataAssinatura();

        if (dados.tipo() == TipoPeriodicidade.MENSAL) {
            this.dataRenovacao  = dataInicio.plusMonths(1);
        } else {
            this.dataRenovacao  = dataInicio.plusYears(1);
        }

    }
}
