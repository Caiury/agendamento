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

			BigDecimal valorTaxa = calculoTaxa(agendamento);
			
			agendamento.setTaxa(valorTaxa);
			
			return agendamentoRepository.save(agendamento);
		}

		return null;
	}

	public BigDecimal calculoTaxa(Agendamento agendamento) {
		return agendamento.getValorTransferencia().multiply(BigDecimal.valueOf(3))
				.divide(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(3));
	}

}
