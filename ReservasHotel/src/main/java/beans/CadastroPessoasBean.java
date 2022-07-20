package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Pessoa;
import modelo.PessoaFisica;
import modelo.PessoaJuridica;
import modelo.Sexo;

@ManagedBean
@SessionScoped
public class CadastroPessoasBean {

	private Pessoa pessoaSelecionada;
	private Collection<Pessoa> lista;
	private String tipoNovaPessoa;
	private Locale locale;
	
	public CadastroPessoasBean() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		pessoaSelecionada = new PessoaFisica();
		lista = new ArrayList<Pessoa>();
		
		for (int i = 0; i < 10; i++) {
			Pessoa p = (i%2==0) ? new PessoaFisica() : new PessoaJuridica();
			p.setNome(String.format("Fulano %02d", i));
			p.setEmail(String.format("Fulano%02d@teste.com", i));
			p.setTelefone(String.format("9999.88%02d", i));
			
			lista.add(p);
		}
	}
	
	public void criar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (tipoNovaPessoa == null) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Você deve especificar o tipo", ""));
			return;
		}
		
		if (tipoNovaPessoa.equals("PF")) {
			pessoaSelecionada = new PessoaFisica();
		}
		else if (tipoNovaPessoa.equals("PJ")) {
			pessoaSelecionada = new PessoaJuridica();
		}
		
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Preencha os dados abaixo:", ""));
	}
	
	public void salvar() {
		if (!lista.contains(pessoaSelecionada)) {
			lista.add(pessoaSelecionada);
		}
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso", ""));
	}
	
	public String cancelar() {
		pessoaSelecionada = null;
		tipoNovaPessoa = null;
		return "inicio";
	}
	
	public void excluir() {
		lista.remove(pessoaSelecionada);
		pessoaSelecionada = null;
		String mensagem = ResourceBundle.getBundle("bundles.mensagens", locale).getString("excluida");
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}
	
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	
	public Collection<Pessoa> getLista() {
		return lista;
	}
	
	public void setLista(Collection<Pessoa> lista) {
		this.lista = lista;
	}
	
	public String getTipoNovaPessoa() {
		return tipoNovaPessoa;
	}

	public void setTipoNovaPessoa(String tipoNovaPessoa) {
		this.tipoNovaPessoa = tipoNovaPessoa;
	}
	
	//Métodos getters de atributos inexistentes 
	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public boolean isPessoaFisicaSelecionada() {
		return pessoaSelecionada instanceof PessoaFisica;
	}
	
	public boolean isPessoaJuridicaSelecionada() {
		return pessoaSelecionada instanceof PessoaJuridica;
	}
}