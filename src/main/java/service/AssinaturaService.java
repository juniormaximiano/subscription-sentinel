package service;

import lombok.RequiredArgsConstructor;
import model.Assinatura;
import model.TipoPeriodicidade;
import org.springframework.stereotype.Service;
import repository.AssinaturaRepository;

@Service
@RequiredArgsConstructor
public class AssinaturaService {

    private final AssinaturaRepository AssinaturaRepo;

    public Assinatura cadastrarAssinatura(Assinatura novaAssinatura) {
        var data = novaAssinatura.getDataInicio();

        if (novaAssinatura.getPeriodicidade() == TipoPeriodicidade.MENSAL) {
            novaAssinatura.setDataRenovacao(data.plusMonths(1));
        } else {
            novaAssinatura.setDataRenovacao(data.plusYears(1));
        }

        return AssinaturaRepo.save(novaAssinatura);
    }
}
