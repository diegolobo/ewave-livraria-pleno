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

import br.com.ewave.livraria.model.InstituicaoEnsino;
import br.com.ewave.livraria.service.InstituicaoService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;

@Path("/instituicoes")
public class InstituicaoResource {

	@Inject
	private InstituicaoService service;
	
	@ResourcePath("instituicoes/index.html")
	Template index;
	
	@ResourcePath("instituicoes/novo.html")
	Template novo;
	
	@ResourcePath("instituicoes/delete.html")
	Template delete;
	
	@ResourcePath("instituicoes/editar.html")
	Template editar;
	
	@GET
	@Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return index.data("instituicoes", service.obterTodos());
    }
	
	@GET
	@Path("/novo")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance novo(){
		return novo.instance();
	}
	
	@POST
	@Path("/adicionarInstituicao")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response adicionarInstituicao(@FormParam("nome") String nome, @FormParam("endereco") String endereco, @FormParam("cnpj") String cnpj, 
			@FormParam("telefone") String telefone){
		
		InstituicaoEnsino inst = new InstituicaoEnsino(nome, endereco, cnpj, telefone);
		
		service.salvar(inst);
		return Response.seeOther(URI.create("/instituicoes")).build();
	}
	
	@GET
	@Path("/editar/{id}")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance editar(@PathParam("id") Long id){
		InstituicaoEnsino inst = service.findById(id);
		return editar.data("inst", inst);
	}
	
	@POST
	@Path("/editarInstituicao/")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response editarInstituicao(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("endereco") String endereco, @FormParam("cnpj") String cnpj, 
			@FormParam("telefone") String telefone){
		
		InstituicaoEnsino inst = new InstituicaoEnsino(nome, endereco, cnpj, telefone);
		inst.setId(id);
		service.salvar(inst);
		return Response.seeOther(URI.create("/instituicoes")).build();
	}
	
	@GET
	@Path("/excluir/{id}")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance excluir(@PathParam("id") Long id){
		InstituicaoEnsino inst = service.findById(id);
		return delete.data("inst", inst);
	}
	
	@POST
	@Path("{id}")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response excluirInstituicao(@FormParam("id") Long id){
		service.deleteById(id);
		return Response.seeOther(URI.create("/instituicoes")).build();
	}
}