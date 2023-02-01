package com.caiury.brasil.agendamento.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caiury.brasil.agendamento.model.Agendamento;
import com.caiury.brasil.agendamento.repository.AgendamentoRepository;
import com.caiury.brasil.agendamento.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@GetMapping
	public ResponseEntity<List<Agendamento>> buscarAgendamentos() {
		List<Agendamento> agendamentos = agendamentoService.buscarAgendamentos();

		return ResponseEntity.ok(agendamentos);
	}

	@GetMapping("{id}")
	public ResponseEntity<Agendamento> BuscarCozinha(@PathVariable Long id) {
		Optional<Agendamento> agendamento = agendamentoRepository.findById(id);

		if (agendamento.isPresent()) {
			return ResponseEntity.ok(agendamento.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Agendamento> cadastrarAgendamento(@RequestBody @Valid Agendamento agendamentoRequest) {

		Agendamento agendamento = agendamentoService.salvarAgendamento(agendamentoRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);

	}

}
