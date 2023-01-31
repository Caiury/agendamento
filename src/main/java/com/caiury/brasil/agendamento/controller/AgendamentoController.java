package com.caiury.brasil.agendamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caiury.brasil.agendamento.model.Agendamento;
import com.caiury.brasil.agendamento.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping
	public ResponseEntity<List<Agendamento>> buscarAgendamentos() {
		List<Agendamento> agendamentos = agendamentoService.buscarAgendamentos();

		return ResponseEntity.ok(agendamentos);
	}
	
//	@PostMapping
//	public ResponseEntity<Agendamento>cadastrarAgendamento(@RequestBody Agendamento agendamento){
//		
//	}

}
