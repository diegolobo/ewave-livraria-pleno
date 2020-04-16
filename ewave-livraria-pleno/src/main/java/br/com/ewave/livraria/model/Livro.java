package br.com.ewave.livraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Livro {
	
	public Livro() {
		
	}
	
	public Livro(String titulo, String genero, String autor, String sinopse) {
		setTitulo(titulo);
		setGenero(genero);
		setAutor(autor);
		setSinopse(sinopse);
	}
	
	@Id @GeneratedValue private Long id;
	private String titulo;
	private String genero;
	private String autor;
	private String sinopse;
	private Boolean ativo;
	private byte[]  capa;
	
	public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public byte[]  getCapa() {
		return capa;
	}
	public void setCapa(byte[]  capa) {
		this.capa = capa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
