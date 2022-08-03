package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Pessoa;
import modelo.PessoaFisica;
import modelo.PessoaJuridica;
import modelo.Sexo;

@ManagedBean
@SessionScoped
public class CadastroPessoasBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Pessoa pessoaSelecionada;
	private Collection<Pessoa> lista;
	private String tipoNovaPessoa;
	private String codigoGet;
	
	@ManagedProperty(value = "#{geralBean}")
	private GeralBean geral;
	
	@PersistenceContext(unitName = "ReservasHotel")
	private EntityManager em;
	
	public CadastroPessoasBean() {
//		lista = new ArrayList<Pessoa>();		
//		for (int i = 0; i < 10; i++) {
//			Pessoa p = (i%2==0) ? new PessoaFisica() : new PessoaJuridica();
//			p.setCodigo(i + 1);
//			p.setNome(String.format("Fulano %02d", i));
//			p.setEmail(String.format("Fulano%02d@teste.com", i));
//			p.setTelefone(String.format("9999.88%02d", i));
//			
//			lista.add(p);
//		}
		
	}
	
	@PostConstruct
	public void iniciar() {
		lista = em.createQuery("select p from Pessoa p").getResultList();

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
	
	public String salvar() {
		if (!lista.contains(pessoaSelecionada)) {
			lista.add(pessoaSelecionada);
		}
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getFlash().put("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso", ""));
		
		return "sucesso";
	}
	
	public String cancelar() {
		pessoaSelecionada = null;
		tipoNovaPessoa = null;
		return "inicio";
	}
	
	public void excluir() {
		lista.remove(pessoaSelecionada);
		pessoaSelecionada = null;
		String mensagem = ResourceBundle.getBundle("bundles.mensagens", geral.getLocale()).getString("excluida");
		
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
	
	public String getCodigoGet() {
		return codigoGet;
	}

	public void setCodigoGet(String codigoGet) {
		this.codigoGet = codigoGet;
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("SETOU O CODIGOGET PARA: " + codigoGet);
	}

	public GeralBean getGeral() {
		return geral;
	}

	public void setGeral(GeralBean geral) {
		this.geral = geral;
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
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
	
	public void ouvinteAjax(AjaxBehaviorEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("AJAX " + event.getPhaseId());
	}
	
	public void ouvinteAjax(ValueChangeEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("AJAX VALUE CHANGE " + event.getPhaseId());
	}
	
	public void viewListener(ComponentSystemEvent event) {
		if (codigoGet != null && !codigoGet.isEmpty()) {
			pessoaSelecionada = ((ArrayList<Pessoa>) lista).get(Integer.parseInt(codigoGet) - 1);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("ViewListener executado. Pessoa: " + pessoaSelecionada.getNome());
		}
	}
}
