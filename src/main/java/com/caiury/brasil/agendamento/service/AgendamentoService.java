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

		if (dias == 0 || agendamento.getValorTransferencia().compareTo(BigDecimal.valueOf(1000)) <= 0) {

			agendamento.setTaxa(calculoTaxa(agendamento));

			return agendamentoRepository.save(agendamento);
		}

		if (dias >= 1 && dias <= 10 || agendamento.getValorTransferencia().compareTo(BigDecimal.valueOf(1001)) >= 0
				&& agendamento.getValorTransferencia().compareTo(BigDecimal.valueOf(2000)) <= 0) {
			agendamento.setTaxa(BigDecimal.valueOf(12));

			return agendamentoRepository.save(agendamento);

		}

		if (dias > 10 && dias <= 20) {

			agendamento.setTaxa(calculoTaxa(agendamento, BigDecimal.valueOf(8.2)));

			return agendamentoRepository.save(agendamento);
		}

		if (dias > 20 && dias <= 30) {

			agendamento.setTaxa(calculoTaxa(agendamento, BigDecimal.valueOf(6.9)));

			return agendamentoRepository.save(agendamento);

		}

		if (dias > 30 && dias <= 40) {

			agendamento.setTaxa(calculoTaxa(agendamento, BigDecimal.valueOf(4.7)));

			return agendamentoRepository.save(agendamento);

		}

		agendamento.setTaxa(calculoTaxa(agendamento, BigDecimal.valueOf(1.7)));

		return agendamentoRepository.save(agendamento);

	}

	public BigDecimal calculoTaxa(Agendamento agendamento) {
		return agendamento.getValorTransferencia().multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(100))
				.add(BigDecimal.valueOf(3));
	}

	public BigDecimal calculoTaxa(Agendamento agendamento, BigDecimal porcetangem) {
		return agendamento.getValorTransferencia().multiply(porcetangem).divide(BigDecimal.valueOf(100));
	}

}
