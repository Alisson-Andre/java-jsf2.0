package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private Date Data;
	private double valor;
	private Pessoa cliente;
	private Collection<DiariaReservada> diarias;

	public Reserva() {
		super();
	} 
	
	@Id 
	@GeneratedValue
	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	} 
	
	public Date getData() {
		return this.Data;
	}

	public void setData(Date Data) {
		this.Data = Data;
	}  
	
	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}  
	
	@ManyToOne
	@JoinColumn(name = "cod_pessoa")
	public Pessoa getCliente() {
		return this.cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	@OneToMany(mappedBy = "reserva",
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	public Collection<DiariaReservada> getDiarias() {
		return diarias;
	}

	public void setDiarias(Collection<DiariaReservada> diarias) {
		this.diarias = diarias;
	}
   
}
