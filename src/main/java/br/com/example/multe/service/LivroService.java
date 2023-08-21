package br.com.example.multe.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.multe.exception.ObjectNotFoundException;
import br.com.example.multe.model.Livro;
import br.com.example.multe.repository.LivroRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;

	public List<Livro> findAll() {
		return repository.findAll();
	}

	public Livro insert(Livro livro) {
		return repository.save(livro);
	}

	public byte[] exportToXml(Long livroId) throws Exception {
		try {
			Livro livro = findById(livroId);

			JAXBContext context = JAXBContext.newInstance(Livro.class);
			Marshaller marshaller = context.createMarshaller();
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			marshaller.marshal(livro, os);
			return os.toByteArray();
		} catch (JAXBException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Livro findById(Long livroId) {
		Optional<Livro> livro = repository.findById(livroId);
		return livro.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! Id: " + livroId));
	}
}
