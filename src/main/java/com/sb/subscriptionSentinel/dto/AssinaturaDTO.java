package com.sb.subscriptionSentinel.dto;

import com.sb.subscriptionSentinel.model.TipoPeriodicidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssinaturaDTO(
        String nomePlano,
        BigDecimal valor,
        TipoPeriodicidade tipo,
        LocalDate dataAssinatura
) {}
