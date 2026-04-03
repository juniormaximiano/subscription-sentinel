package com.sb.subscriptionSentinel.dto;

import java.math.BigDecimal;

public record ResumoFinanceiroDTO(
        BigDecimal totalMensal,
        long totalAssinaturas,
        BigDecimal maiorAssinatura
) {
}
