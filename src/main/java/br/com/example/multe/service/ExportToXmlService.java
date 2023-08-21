package br.com.example.multe.service;

import br.com.example.multe.model.Livro;
public interface ExportToXmlService {
    void exportLivroToXml(Livro livro);

    Livro findLivroById(Long id);
}
