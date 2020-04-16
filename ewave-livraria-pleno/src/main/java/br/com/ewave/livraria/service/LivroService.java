package br.com.ewave.livraria.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.ewave.livraria.model.Livro;
import br.com.ewave.livraria.repository.LivroRepository;

@ApplicationScoped
public class LivroService {

	@Inject
	private LivroRepository repository;
	
	public List<Livro> obterTodos(){
		
		return repository.getAllBooks();
	}
	
	public Livro findById(Long id){
		
		return repository.findById(id);		
	}
	
	public void salvar(Livro livro){
		
		if(livro.getId() != null && livro.getId() > 0) {
			Livro livroDb = repository.findById(livro.getId());
			livroDb.setTitulo(livro.getTitulo());
			livroDb.setGenero(livro.getGenero());
			livroDb.setAutor(livro.getAutor());
			livroDb.setSinopse(livro.getSinopse());
			repository.persist(livroDb);
		}
		else {
			livro.setAtivo(true);
			repository.persist(livro);
		}
	}

	public void deleteById(Long id) {
		Livro livro = repository.findById(id);
		livro.setAtivo(false);
		repository.persist(livro);		
	}
}
