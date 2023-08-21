package br.com.example.multe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.multe.model.Pagina;
import br.com.example.multe.repository.PaginaRepository;

@Service
public class PaginaService {

	@Autowired
	private PaginaRepository repository;
	
	public Pagina insert(Pagina pagina) {
		return repository.save(pagina);
	}
}
