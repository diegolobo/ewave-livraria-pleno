package br.com.ewave.livraria.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.ewave.livraria.model.Usuario;
import br.com.ewave.livraria.repository.UsuarioRepository;

@ApplicationScoped
public class UsuarioService {

	@Inject
	private UsuarioRepository repository;
	
	public List<Usuario> obterTodos(){
		
		return repository.getAllUsers();
	}
	
	public Usuario findById(Long id){
		
		return repository.findById(id);		
	}
	
	public void salvar(Usuario user){
		
		if(user.getId() != null && user.getId() > 0) {
			Usuario userDb = repository.findById(user.getId());
			userDb.setNome(user.getNome());
			userDb.setEndereco(user.getEndereco());
			userDb.setCpf(user.getCpf());
			userDb.setInstituicaoEnsinoId(user.getInstituicaoEnsinoId());
			userDb.setTelefone(user.getTelefone());
			userDb.setEmail(user.getEmail());
			repository.persist(userDb);
		}
		else {
			user.setAtivo(true);
			repository.persist(user);
		}
	}

	public void deleteById(Long id) {
		Usuario user = repository.findById(id);
		user.setAtivo(false);
		repository.persist(user);		
	}
}
