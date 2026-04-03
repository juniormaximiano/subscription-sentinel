package com.sb.subscriptionSentinel.service;

import com.sb.subscriptionSentinel.dto.AssinaturaDTO;
import com.sb.subscriptionSentinel.dto.AssinaturaResponseDTO;
import com.sb.subscriptionSentinel.dto.ResumoFinanceiroDTO;
import lombok.RequiredArgsConstructor;
import com.sb.subscriptionSentinel.model.Assinatura;
import org.springframework.stereotype.Service;
import com.sb.subscriptionSentinel.repository.AssinaturaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepo;

    public Assinatura cadastrarAssinatura(AssinaturaDTO dados) {
         Assinatura novaAssinatura = new Assinatura(dados);
        return assinaturaRepo.save(novaAssinatura);
    }

    public List<AssinaturaResponseDTO> listarAssinaturas() {
        return assinaturaRepo.findAll()
                .stream()
                .map(AssinaturaResponseDTO::new)
                .toList();
    }

    public Optional<Assinatura> buscarAssinaturaPorId(long id) {
        return assinaturaRepo.findById(id);
    }

    public void deletarAssinatura(long id) {
        if(!assinaturaRepo.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada!");
        } else {
            assinaturaRepo.deleteById(id);
        }
    }

    public AssinaturaResponseDTO atualizarAssinatura(long id, AssinaturaDTO dadosNovos) {

       var assinaturaExistente =  assinaturaRepo.findById(id).orElseThrow(() -> new RuntimeException("Assinatura inexistente!"));
            assinaturaExistente.setNomeServico(dadosNovos.nomePlano());
            assinaturaExistente.setValor(dadosNovos.valor());
            assinaturaRepo.save(assinaturaExistente);
            return  new AssinaturaResponseDTO(assinaturaExistente);

    }

    public ResumoFinanceiroDTO calcularResumo(){
        var ativas = assinaturaRepo.findAll().stream()
                .filter(Assinatura::isAtivo)
                .toList();

        var total = assinaturaRepo.findAll().stream()
                .map(Assinatura::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var quantidade = (long) ativas.size();

        var maior = assinaturaRepo.findAll().stream().map(Assinatura::getValor)
                .max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        return new ResumoFinanceiroDTO(total, quantidade, maior);
    }


}
