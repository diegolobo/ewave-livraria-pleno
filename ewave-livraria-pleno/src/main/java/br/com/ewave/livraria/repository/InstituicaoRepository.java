package br.com.ewave.livraria.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.com.ewave.livraria.model.InstituicaoEnsino;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class InstituicaoRepository implements PanacheRepository<InstituicaoEnsino>{

	@Transactional
	public List<InstituicaoEnsino> getAllUniversities(){
		
		 List<InstituicaoEnsino> inst = find("ativo", true).list();	 

		 return inst;
	}
}
