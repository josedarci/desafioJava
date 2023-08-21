package br.com.example.multe.repository;

import br.com.example.multe.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	Livro findByTitulo(String titulo);
}