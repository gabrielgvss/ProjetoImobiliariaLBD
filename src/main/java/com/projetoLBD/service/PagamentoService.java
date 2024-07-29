package com.projetoLBD.service;

import com.projetoLBD.entity.Aluguel;
import com.projetoLBD.entity.Locacao;
import com.projetoLBD.repository.AluguelRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoService {

    private final AluguelRepository aluguelRepository;

    public PagamentoService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public BigDecimal registrarPagamento(Aluguel aluguel, LocalDate dataPagamento) {
        BigDecimal valorOriginal = aluguel.getValorPago();
        LocalDate dataVencimento = aluguel.getDataVencimento();

        BigDecimal valorComMulta = calcularValorComMulta(valorOriginal, dataVencimento, dataPagamento);

        aluguel.setDataPagamento(dataPagamento);
        aluguel.setValorPago(valorComMulta);

        aluguelRepository.salvarOuAlterar(aluguel);

        return valorComMulta;
    }

    private BigDecimal calcularValorComMulta(BigDecimal valorOriginal, LocalDate dataVencimento, LocalDate dataPagamento) {
        if (dataPagamento.isBefore(dataVencimento) || dataPagamento.isEqual(dataVencimento)) {
            return valorOriginal; // Sem multa se o pagamento for no prazo.
        }

        long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
        BigDecimal taxaMultaPorDia = new BigDecimal("0.0033"); // 0,33% ao dia
        BigDecimal limiteMulta = new BigDecimal("0.20"); // 20%

        // Calcula a multa acumulada
        BigDecimal multa = valorOriginal.multiply(taxaMultaPorDia).multiply(new BigDecimal(diasAtraso));
        BigDecimal multaLimite = valorOriginal.multiply(limiteMulta);

        // Aplica o limite de multa
        if (multa.compareTo(multaLimite) > 0) {
            multa = multaLimite;
        }

        return valorOriginal.add(multa);
    }
}
