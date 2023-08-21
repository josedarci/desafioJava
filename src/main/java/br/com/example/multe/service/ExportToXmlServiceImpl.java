package br.com.example.multe.service;

import java.util.Optional;

import br.com.example.multe.model.Capitulo;
import br.com.example.multe.model.Livro;
import br.com.example.multe.model.Pagina;
import br.com.example.multe.repository.LivroRepository;

import java.io.FileWriter;


import org.springframework.stereotype.Service;

@Service
public class ExportToXmlServiceImpl implements ExportToXmlService {
    private final LivroRepository livroRepository;

    public ExportToXmlServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void exportLivroToXml(Long livroId) {
        Optional<Livro> optionalLivro = livroRepository.findById(livroId);

        if (optionalLivro.isPresent()) {
            Livro livro = optionalLivro.get();
            

            String xmlContent = generateXmlContent(livro);

            String xmlFilePath = "livro_" + livroId + ".xml";
            try (FileWriter writer = new FileWriter(xmlFilePath)) {
                writer.write(xmlContent);
                System.out.println("Arquivo XML gerado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao gerar o arquivo XML: " + e.getMessage());
            }
        } else {
            System.err.println("Livro não encontrado com o ID: " + livroId);
        }
    }

    private String generateXmlContent(Livro livro) {
        StringBuilder xmlContent = new StringBuilder();

        xmlContent.append("<Livro>\n");
        xmlContent.append("  <Titulo>").append(livro.getTitulo()).append("</Titulo>\n");

        for (Capitulo capitulo : livro.getCapitulos()) {
            xmlContent.append("  <Capitulo>\n");
            xmlContent.append("    <TituloCapitulo>").append(capitulo.getTitulo()).append("</TituloCapitulo>\n");

            for (Pagina pagina : capitulo.getPaginas()) {
                xmlContent.append("    <Pagina>\n");
                xmlContent.append("      <NumeroPagina>").append(pagina.getNumero()).append("</NumeroPagina>\n");
                
                // Adicione o conteúdo da página se ela existir
                String conteudoPagina = pagina.getConteudo();
                if (conteudoPagina != null && !conteudoPagina.isEmpty()) {
                    xmlContent.append("      <Conteudo>").append(conteudoPagina).append("</Conteudo>\n");
                }

                xmlContent.append("    </Pagina>\n");
            }

            xmlContent.append("  </Capitulo>\n");
        }

        xmlContent.append("</Livro>");

        return xmlContent.toString();
    }


	@Override
	public void exportLivroToXml(Livro livro) {
		 if (livro != null) {
		        String xmlContent = generateXmlContent(livro);

		        String xmlFilePath = "livro_" + livro.getId() + ".xml";
		        try (FileWriter writer = new FileWriter(xmlFilePath)) {
		            writer.write(xmlContent);
		            System.out.println("Arquivo XML gerado com sucesso!");
		        } catch (Exception e) {
		            System.err.println("Erro ao gerar o arquivo XML: " + e.getMessage());
		        }
		    } else {
		        System.err.println("Livro é nulo. Não é possível exportar para XML.");
		    }
		
	}

	@Override
	public Livro findLivroById(Long id) {
		return livroRepository.findById(id).orElse(null);
	}
}