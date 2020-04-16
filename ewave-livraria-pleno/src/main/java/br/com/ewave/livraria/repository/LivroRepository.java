package br.com.ewave.livraria.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.com.ewave.livraria.model.Livro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class LivroRepository implements PanacheRepository<Livro>{

	@Transactional
	public List<Livro> getAllBooks(){
		
		 List<Livro> livros = find("ativo", true).list();	 

		 return livros;
	}
}
