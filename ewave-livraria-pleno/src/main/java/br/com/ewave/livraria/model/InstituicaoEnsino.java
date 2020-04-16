package br.com.ewave.livraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InstituicaoEnsino {
	
	public InstituicaoEnsino() {
		
	}
	
	public InstituicaoEnsino(String nome, String endereco, String cnpj, String telefone) {
		setNome(nome);
		setEndereco(endereco);
		setCnpj(cnpj);
		setTelefone(telefone);
	}
	
	@Id @GeneratedValue private Long id;
	private String nome;
	private String endereco;
	private String cnpj;
	private String telefone;
	private Boolean ativo;
	
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Long getId(){
        return id;
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
}
