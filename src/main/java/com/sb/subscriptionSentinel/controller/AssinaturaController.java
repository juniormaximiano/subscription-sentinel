package com.sb.subscriptionSentinel.controller;

import com.sb.subscriptionSentinel.dto.AssinaturaDTO;
import com.sb.subscriptionSentinel.dto.AssinaturaResponseDTO;
import com.sb.subscriptionSentinel.dto.ResumoFinanceiroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sb.subscriptionSentinel.service.AssinaturaService;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @PostMapping
    public ResponseEntity<AssinaturaResponseDTO> salvar(@RequestBody AssinaturaDTO dados) {
        var nova = assinaturaService.cadastrarAssinatura(dados);
        return ResponseEntity.status(201).body(new AssinaturaResponseDTO(nova));
    }

    @GetMapping
    public ResponseEntity<List<AssinaturaResponseDTO>> listar() {
        return ResponseEntity.ok(assinaturaService.listarAssinaturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssinaturaResponseDTO> buscarPorId(@PathVariable long id) {
        return assinaturaService.buscarAssinaturaPorId(id)
                .map(AssinaturaResponseDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAssinatura(@PathVariable long id) {
        assinaturaService.deletarAssinatura(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssinaturaResponseDTO> atualizarAssinatura(@PathVariable long id, @RequestBody AssinaturaDTO dados) {
        var dtoAtualizado = assinaturaService.atualizarAssinatura(id, dados);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoFinanceiroDTO> buscarResumoFinanceiro() {
        return ResponseEntity.ok(assinaturaService.calcularResumo());
    }
}