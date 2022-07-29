package modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Quarto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codigo;
	private String numero;
	private Collection<Diaria> diaria;
	private TipoQuarto tipoQuarto;
	
	
	public Quarto() {
		super();
	}
	
	@Id
	@GeneratedValue(generator = "genquarto")
	@SequenceGenerator(name = "genquarto", sequenceName = "quarto_codigo_seq")
	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	} 

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@OneToMany
	public Collection<Diaria> getDiaria() {
		return diaria;
	}

	public void setDiaria(Collection<Diaria> diaria) {
		this.diaria = diaria;
	}

	@Enumerated
	public TipoQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(TipoQuarto tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}
	
}
