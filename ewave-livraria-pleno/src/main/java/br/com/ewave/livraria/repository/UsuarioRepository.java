package br.com.ewave.livraria.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.com.ewave.livraria.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{

	@Transactional
	public List<Usuario> getAllUsers(){
		
		 List<Usuario> users = find("ativo", true).list();	 

		 return users;
	}
}
