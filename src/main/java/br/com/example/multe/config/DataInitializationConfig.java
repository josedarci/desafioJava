package br.com.example.multe.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import br.com.example.multe.model.Capitulo;
import br.com.example.multe.model.Livro;
import br.com.example.multe.model.Pagina;
import br.com.example.multe.service.CapituloService;
import br.com.example.multe.service.LivroService;
import br.com.example.multe.service.PaginaService;

@Configuration
@Order(1)
public class DataInitializationConfig {

	@Autowired
	private LivroService livroService;

	@Autowired
	private CapituloService capituloService;

	@Autowired
	private PaginaService paginaService;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeData() {
		List<Livro> list = livroService.findAll();

		if (list.isEmpty()) {
			salvarLivro1();
			salvarLivro2();
		}
	}
	
	private void salvarLivro1() {
		Livro livro = salvarLivro("Manual de Reparo de Computadores", "João Silva", 2020);
		
		Capitulo capitulo1 = salvarCapitulo(livro, "Introdução aos Componentes", 1);
        Capitulo capitulo2 = salvarCapitulo(livro, "Diagnóstico de Problemas", 2);
        Capitulo capitulo3 = salvarCapitulo(livro, "Substituição de Hardware", 3);

        salvarPagina(capitulo1, 1, "Neste capítulo, vamos explorar os principais componentes internos de um computador.");
        salvarPagina(capitulo1, 2, "A placa-mãe é o circuito principal que conecta todos os componentes.");
        salvarPagina(capitulo2, 3, "Diagnóstico de problemas é uma etapa fundamental para resolver falhas de hardware.");
        salvarPagina(capitulo3, 4, "Ao substituir peças, certifique-se de desligar o computador e desconectar a fonte de alimentação.");
        salvarPagina(capitulo3, 5, "Este capítulo cobre a substituição da placa gráfica e do disco rígido.");
	}
	
	private void salvarLivro2() {
		Livro livro = salvarLivro("Guia Completo de Gadgets Modernos", "Maria Oliveira", 2022);
		
		Capitulo capitulo1 = salvarCapitulo(livro, "Smartphones e Tablets", 1);
        Capitulo capitulo2 = salvarCapitulo(livro, "Dispositivos Vestíveis", 2);

        salvarPagina(capitulo1, 1, "Os smartphones modernos oferecem uma ampla gama de recursos e aplicativos.");
        salvarPagina(capitulo1, 2, "Aqui estão algumas dicas para maximizar a vida útil da bateria do seu dispositivo.");
        salvarPagina(capitulo2, 3, "Dispositivos vestíveis, como smartwatches, são cada vez mais populares para monitorar a saúde.");
        salvarPagina(capitulo2, 4, "Este capítulo explora os diferentes tipos de dispositivos de realidade virtual disponíveis no mercado.");
	}

	private Livro salvarLivro(String titulo, String autor, Integer ano) {
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setAno(ano);
		livroService.insert(livro);
		return livro;
	}
	
	private Capitulo salvarCapitulo(Livro livro, String titulo, int numero) {
        Capitulo capitulo = new Capitulo();
        capitulo.setTitulo(titulo);
        capitulo.setLivro(livro);
        capitulo.setNumero(numero);
        capituloService.insert(capitulo);
        return capitulo;
    }
	
	private void salvarPagina(Capitulo capitulo, int numero, String conteudo) {
        Pagina pagina = new Pagina();
        pagina.setCapitulo(capitulo);
        pagina.setNumero(numero);
        pagina.setConteudo(conteudo);
        paginaService.insert(pagina);
    }
}
