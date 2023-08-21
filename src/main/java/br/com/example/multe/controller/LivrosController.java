package br.com.example.multe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.multe.exception.ObjectNotFoundException;
import br.com.example.multe.service.LivroService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livros")
public class LivrosController {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping(value = "/export/{livroId}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<byte[]> exportToXml(@PathVariable Long livroId) {
		try {
			byte[] xmlBytes = livroService.exportToXml(livroId);
			
			HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setContentLength(xmlBytes.length);
            
			return new ResponseEntity<>(xmlBytes, headers, HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

