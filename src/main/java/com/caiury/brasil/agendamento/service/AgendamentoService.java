package com.caiury.brasil.agendamento.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiury.brasil.agendamento.model.Agendamento;
import com.caiury.brasil.agendamento.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public List<Agendamento> buscarAgendamentos() {
		return agendamentoRepository.findAll();
	}

	public Agendamento salvarAgendamento(Agendamento agendamento) {
		agendamento.setTaxa(BigDecimal.ZERO);

		long dias = ChronoUnit.DAYS.between(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());

		BigDecimal valorTaxa = null;

		if (dias == 0 || agendamento.getValorTransferencia().compareTo(BigDecimal.valueOf(1000)) <= 0) {

			valorTaxa = calculoTaxa(agendamento);

			agendamento.setTaxa(valorTaxa);

			return agendamentoRepository.save(agendamento);
		}

		if (dias >= 1 && dias <= 10 || agendamento.getValorTransferencia().compareTo(BigDecimal.valueOf(1001)) >= 0
				&& agendamento.getValorTransferencia().compareTo(BigDecimal.valueOf(2000)) <= 0) {
			agendamento.setTaxa(BigDecimal.valueOf(12));

			return agendamentoRepository.save(agendamento);

		}

		if (dias > 10 && dias <= 20) {

			valorTaxa = calculoTaxa(agendamento, BigDecimal.valueOf(8.2));

			agendamento.setTaxa(valorTaxa);

			return agendamentoRepository.save(agendamento);
		}

		if (dias > 20 && dias <= 30) {

			valorTaxa = calculoTaxa(agendamento, BigDecimal.valueOf(6.9));

			agendamento.setTaxa(valorTaxa);

			return agendamentoRepository.save(agendamento);

		}

		if (dias > 30 && dias <= 40) {

			valorTaxa = calculoTaxa(agendamento, BigDecimal.valueOf(4.7));

			agendamento.setTaxa(valorTaxa);

			return agendamentoRepository.save(agendamento);

		}

		if (dias > 40) {

			valorTaxa = calculoTaxa(agendamento, BigDecimal.valueOf(1.7));

			agendamento.setTaxa(valorTaxa);

			return agendamentoRepository.save(agendamento);

		}

		return null;
	}

	public BigDecimal calculoTaxa(Agendamento agendamento) {
		return agendamento.getValorTransferencia().multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(100))
				.add(BigDecimal.valueOf(3));
	}

	public BigDecimal calculoTaxa(Agendamento agendamento, BigDecimal porcetangem) {
		return agendamento.getValorTransferencia().multiply(porcetangem).divide(BigDecimal.valueOf(100));
	}

}
