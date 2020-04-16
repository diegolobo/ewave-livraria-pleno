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

import br.com.ewave.livraria.model.Usuario;
import br.com.ewave.livraria.service.InstituicaoService;
import br.com.ewave.livraria.service.UsuarioService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;

@Path("/usuarios")
public class UsuarioResource {

	@Inject
	private UsuarioService service;
	
	@Inject
	private InstituicaoService serviceInstituicao;
	
	@ResourcePath("usuarios/index.html")
	Template index;
	
	@ResourcePath("usuarios/novo.html")
	Template novo;
	
	@ResourcePath("usuarios/delete.html")
	Template delete;
	
	@ResourcePath("usuarios/editar.html")
	Template editar;
	
	@GET
	@Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return index.data("usuarios", service.obterTodos());
    }
	
	@GET
	@Path("/novo")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance novo(){
		Usuario user = new Usuario();
		user.instituicoes = serviceInstituicao.obterTodos();
		return novo.data("usuario", user);
	}
	
	@POST
	@Path("/adicionarUsuario")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response adicionarUsuario(@FormParam("nome") String nome, @FormParam("endereco") String endereco, @FormParam("cpf") String cpf, 
			@FormParam("instituicaoEnsinoId") Long instituicaoEnsinoId, @FormParam("telefone") String telefone, @FormParam("email") String email){
		
		Usuario usuario = new Usuario(nome, endereco, cpf, instituicaoEnsinoId, telefone, email);
		
		service.salvar(usuario);
		return Response.seeOther(URI.create("/usuarios")).build();
	}
	
	@GET
	@Path("/editar/{id}")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance editar(@PathParam("id") Long id){
		Usuario usuario = service.findById(id);
		usuario.instituicoes = serviceInstituicao.obterTodos();
		return editar.data("usuario", usuario);
	}
	
	@POST
	@Path("/editarUsuario/")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response editarUsuario(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("endereco") String endereco, @FormParam("cpf") String cpf, 
			@FormParam("instituicaoEnsinoId") Long instituicaoEnsinoId, @FormParam("telefone") String telefone, @FormParam("email") String email){
		
		Usuario usuario = new Usuario(nome, endereco, cpf, instituicaoEnsinoId, telefone, email);
		usuario.setId(id);
		service.salvar(usuario);
		return Response.seeOther(URI.create("/usuarios")).build();
	}
	
	@GET
	@Path("/excluir/{id}")
    @Produces(MediaType.TEXT_HTML)
	public TemplateInstance excluir(@PathParam("id") Long id){
		Usuario usuario = service.findById(id);
		return delete.data("usuario", usuario);
	}
	
	@POST
	@Path("{id}")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response excluirUsuario(@FormParam("id") Long id){
		service.deleteById(id);
		return Response.seeOther(URI.create("/usuarios")).build();
	}
}