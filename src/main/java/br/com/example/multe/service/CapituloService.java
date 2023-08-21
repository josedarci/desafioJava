package br.com.example.multe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.multe.model.Capitulo;
import br.com.example.multe.repository.CapituloRepository;

@Service
public class CapituloService {

	@Autowired
	private CapituloRepository repository;
	
	public Capitulo insert(Capitulo capitulo) {
		return repository.save(capitulo);
	}
	
}
