package com.caiury.brasil.agendamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.caiury.brasil.agendamento.model.Agendamento;
import com.caiury.brasil.agendamento.service.AgendamentoService;

public class TesTe {

	public static void main(String[] args) {

		AgendamentoService agendamentoService = new AgendamentoService();
		
		Agendamento agendamento = new Agendamento();
		
		agendamento.setValorTransferencia(BigDecimal.valueOf(2001));
		
		agendamento.setDataAgendamento(LocalDate.now());		

		agendamento.setDataTransferencia(LocalDate.of(2023, 2, 10));
		
		long dias = ChronoUnit.DAYS.between(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());

		System.out.println(dias);
		
	BigDecimal	valorTaxa = agendamento.getValorTransferencia().multiply(BigDecimal.valueOf(8.2)).divide(BigDecimal.valueOf(100));

		System.out.println(valorTaxa);
	}

}
