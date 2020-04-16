package br.com.ewave.livraria.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.ewave.livraria.model.InstituicaoEnsino;
import br.com.ewave.livraria.repository.InstituicaoRepository;

@ApplicationScoped
public class InstituicaoService {

	@Inject
	private InstituicaoRepository repository;
	
	public List<InstituicaoEnsino> obterTodos(){
		
		return repository.getAllUniversities();
	}
	
	public InstituicaoEnsino findById(Long id){
		
		return repository.findById(id);		
	}
	
	public void salvar(InstituicaoEnsino inst){
		
		if(inst.getId() != null && inst.getId() > 0) {
			InstituicaoEnsino instDb = repository.findById(inst.getId());
			instDb.setNome(inst.getNome());
			instDb.setEndereco(inst.getEndereco());
			instDb.setCnpj(inst.getCnpj());
			instDb.setTelefone(inst.getTelefone());
			repository.persist(instDb);
		}
		else {
			inst.setAtivo(true);
			repository.persist(inst);
		}
	}

	public void deleteById(Long id) {
		InstituicaoEnsino inst = repository.findById(id);
		inst.setAtivo(false);
		repository.persist(inst);		
	}
}
