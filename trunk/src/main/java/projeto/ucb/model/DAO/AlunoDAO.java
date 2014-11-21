package projeto.ucb.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import projeto.ucb.model.beans.Aluno;
@Stateless
@LocalBean
public class AlunoDAO {
	@Resource(mappedName = "java:jboss/alunos")
	private DataSource dataSource;
	
	
	private DataSource getDataSource() {
		return dataSource;
	}

	public void add(Aluno aluno){
		try {
			Connection connection = getDataSource().getConnection();
			Statement stm = connection.createStatement();
			String sql = "insert into tb_alunos values("+aluno.getMatricula()+","+aluno.getNota()+","+aluno.getNota()+",);";
			stm.execute(sql);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Aluno findByMatricula(String matricula){
		Aluno aluno = null;
		try{
			Connection connection = getDataSource().getConnection();
			Statement stm = connection.createStatement();
			String sql = "select * from tb_alunos where matricula='"+matricula+"';";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				aluno = new Aluno(rs.getString("matricula"),rs.getString("nome"),rs.getDouble("nota"));
			}
			stm.close();
			return aluno;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Aluno> getAll(){
		try {
			List<Aluno> list = new ArrayList<Aluno>();
			Connection connection = getDataSource().getConnection();
			Statement stm = connection.createStatement();
			String sql = "select * from tb_alunos;";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				Aluno aluno = new Aluno(rs.getString("matricula"),rs.getString("nome"),rs.getDouble("nota"));
				list.add(aluno);
			}
			stm.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
