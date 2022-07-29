package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("unused")
@Entity
public class DiariaAvulsa extends Diaria implements Serializable {
	private static final long serialVersionUID = 1L;

	private double valor;
	private Pessoa cliente;
	
	
	public DiariaAvulsa () {
		super();
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@ManyToOne
	@JoinColumn(name = "cod_pessoa")
	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

}
