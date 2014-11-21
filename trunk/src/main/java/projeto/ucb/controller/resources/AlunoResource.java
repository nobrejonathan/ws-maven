package projeto.ucb.controller.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import projeto.ucb.model.DAO.AlunoDAO;
import projeto.ucb.model.beans.Aluno;
@Stateless
@Path("/alunos")
public class AlunoResource {
	@EJB
	AlunoDAO dao;
	
	public AlunoDAO getDao() {
		return dao;
	}
	public void setDao(AlunoDAO dao) {
		this.dao = dao;
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Aluno>getAll(){
		return getDao().getAll();
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/find")
	public Aluno find(@QueryParam("matricula") String matricula){
		return getDao().findByMatricula(matricula);
	}
}
