package com.sb.subscriptionSentinel.dto;

import com.sb.subscriptionSentinel.model.Assinatura;
import com.sb.subscriptionSentinel.model.TipoPeriodicidade;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssinaturaResponseDTO(
        Long id,
        LocalDate dataInicio,
        LocalDate dataRenovacao,
        String nomePlano,
        BigDecimal valor,
        TipoPeriodicidade tipo,
        String status


){
    public AssinaturaResponseDTO(Assinatura assinatura){
        this(assinatura.getId(), assinatura.getDataInicio(),
                assinatura.getDataRenovacao(), assinatura.getNomeServico(), assinatura.getValor(),
                assinatura.getPeriodicidade(),calcularStatus(assinatura.getDataRenovacao())

        );

    }
    private static String calcularStatus(LocalDate renovacao){
        var hoje  = LocalDate.now();
        if(renovacao.isBefore(hoje)){
            return "VENCIDA/PAGAR";
        }
        if(renovacao.isBefore(hoje.plusDays(7))){
            return "VENCE EM BREVE";
        }

        return "EM DIA";
    }
}

