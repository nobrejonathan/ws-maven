package projeto.ucb.model.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Aluno implements Serializable{
	private static final long serialVersionUID = 8172074762873791092L;
	
	private String matricula;
	private String nome;
	private Double nota;
	
	public Aluno(){
		
	}

	public Aluno(String matricula, String nome, Double nota) {
		setMatricula(matricula);
		setNome(nome);
		setNota(nota);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Aluno){
			Aluno a = (Aluno)obj;
			return getMatricula().equalsIgnoreCase(a.getMatricula());
		}
		return false;
	}
	
}
