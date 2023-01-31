package com.caiury.brasil.agendamento;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.caiury.brasil.agendamento.model.Agendamento;
import com.caiury.brasil.agendamento.service.AgendamentoService;

public class TesTe {

	public static void main(String[] args) {

		AgendamentoService agendamentoService = new AgendamentoService();
		
		Agendamento agendamento = new Agendamento();
		
		agendamento.setValorTransferencia(BigDecimal.valueOf(700));
		
		
		System.out.println(agendamentoService.calculoTaxa(agendamento));
		
	}

}
