package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import modelo.Pessoa;


@SuppressWarnings("unused")
@Entity
@DiscriminatorValue("PF")
public class PessoaFisica extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private Collection<Diaria> diariasPF;
	private Sexo sexo;
	

	public PessoaFisica() {
		super();
	}   
	
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}  
	
	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}  
	
	@Temporal(TemporalType.DATE)
	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@ManyToMany(mappedBy = "hospedes")
	public Collection<Diaria> getDiariasPF() {
		return diariasPF;
	}

	public void setDiariasPF(Collection<Diaria> diariasPF) {
		this.diariasPF = diariasPF;
	}

	@Enumerated
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
}
