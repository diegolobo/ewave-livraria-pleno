package br.com.ewave.livraria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
public class Usuario {
	

	
	public Usuario() {

	}
	
	public Usuario(String nome, String endereco, String cpf, Long instituicaoEnsinoId,
			String telefone, String email) {
		setNome(nome);
		setEndereco(endereco);
		setCpf(cpf);
		setInstituicaoEnsinoId(instituicaoEnsinoId);
		setTelefone(telefone);
		setEmail(email);
	}
	
	@Id @GeneratedValue private Long id;
	private Long instituicaoEnsinoId;
	private String nome;
	private String endereco;
	private String cpf;	
	private String telefone;
	private String email;
	private Boolean ativo;
	
	@OneToMany
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    public List<InstituicaoEnsino> instituicoes;
	
	public Long getId(){
        return id;
    }
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id){
        this.id = id;
    }
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getInstituicaoEnsinoId() {
		return instituicaoEnsinoId;
	}

	public void setInstituicaoEnsinoId(Long instituicaoEnsinoId) {
		this.instituicaoEnsinoId = instituicaoEnsinoId;
	}
}
