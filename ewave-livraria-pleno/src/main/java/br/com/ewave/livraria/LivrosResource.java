package br.com.ewave.livraria;

import java.net.URI;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.ewave.livraria.model.Livro;
import br.com.ewave.livraria.service.LivroService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;

@Path("/livros")
public class LivrosResource {

	@Inject
	private LivroService service;
	
	@ResourcePath("livros/index.html")
	Template index;
	
	@ResourcePath("livros/novo.html")
	Template novo;
	
	@ResourcePath("livros/delete.html")
	Template delete;
	
	@ResourcePath("livros/editar.html")
	Template editar;
	
	@GET
	@Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return index.data("livros", service.obterTodos());
    }
	
	@GET
	@Path("/novo")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance novo(){
		return novo.instance();
	}
	
	@POST
	@Path("/adicionarLivro")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response adicionarLivro(@FormParam("titulo") String titulo, @FormParam("genero") String genero, 
			@FormParam("autor") String autor, @FormParam("sinopse") String sinopse){
		
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setGenero(genero);
		livro.setAutor(autor);
		livro.setSinopse(sinopse);
		
		service.salvar(livro);
		return Response.seeOther(URI.create("/livros")).build();
	}
	
	@GET
	@Path("/editar/{id}")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance editar(@PathParam("id") Long id){
		Livro livro = service.findById(id);
		return editar.data("livro", livro);
	}
	
	@POST
	@Path("/editarLivro/")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response editarLivro(@FormParam("id") Long id, @FormParam("titulo") String titulo, @FormParam("genero") String genero, 
			@FormParam("autor") String autor, @FormParam("sinopse") String sinopse){
		
		Livro livro = new Livro(titulo, genero, autor, sinopse);
		livro.setId(id);
		service.salvar(livro);
		return Response.seeOther(URI.create("/livros")).build();
	}
	
	@GET
	@Path("/excluir/{id}")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance excluir(@PathParam("id") Long id){
		Livro livro = service.findById(id);
		return delete.data("livro", livro);
	}
	
	@POST
	@Path("{id}")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response excluirLivro(@FormParam("id") Long id){
		service.deleteById(id);
		return Response.seeOther(URI.create("/livros")).build();
	}
}