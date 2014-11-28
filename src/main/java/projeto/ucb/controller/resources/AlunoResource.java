package projeto.ucb.controller.resources;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import projeto.ucb.model.DAO.AlunoDAO;
import projeto.ucb.model.beans.Aluno;
import br.ucb.controller.persistence.FilePersistence;
@Path("/alunos")
public class AlunoResource {
	@Context
	HttpServletRequest request;

	AlunoDAO dao;
	public AlunoResource(){
		try {
			InitialContext ctx = new InitialContext();
			AlunoDAO dao = (AlunoDAO)ctx.lookup("java:global/ProjetoMavenWS/AlunoDAO");
			setDao(dao);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public AlunoDAO getDao() {
		return dao;
	}
	public void setDao(AlunoDAO dao) {
		this.dao = dao;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Aluno>getAll(){
		new FilePersistence().persist("log.txt",getRequest().getRemoteAddr()+" "+new Date());
		return getDao().getAll();
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/find")
	public Aluno find(@QueryParam("matricula") String matricula){
		new FilePersistence().persist("log.txt",getRequest().getRemoteAddr()+" "+new Date());
		return getDao().findByMatricula(matricula);
	}
	
}
