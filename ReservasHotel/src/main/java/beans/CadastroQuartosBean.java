package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import modelo.Quarto;
import modelo.TipoQuarto;

@ManagedBean(eager = true)
@ApplicationScoped
public class CadastroQuartosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Quarto quartoAtual;
	private List<Quarto> listaQuarto;
	private HtmlSelectOneMenu selectTipos;
	
	public CadastroQuartosBean() {
		quartoAtual = new Quarto();
		listaQuarto = new ArrayList<Quarto>();
		
		
		selectTipos = new HtmlSelectOneMenu();
		
		UISelectItems itens = new UISelectItems();
		itens.getAttributes().put("value",TipoQuarto.values());
		selectTipos.getChildren().add(itens);
	}
	
	@PostConstruct
	public void construir() {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Cadastro Quartos construido");
	}
	
	@PreDestroy
	public void destruir() {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Cadastro Quartos destruido");
	}
		
	public String salvar() {
		if (!listaQuarto.contains(quartoAtual)) {
			listaQuarto.add(quartoAtual);
		}
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso", ""));
		
		return "sucesso";
	}
	
	public String cancelar() {
		quartoAtual = null;
		return "inicio";
	}
	
	public void excluir() {
		listaQuarto.remove(quartoAtual);
		quartoAtual = new Quarto();
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Quarto excluido com sucesso", ""));
	}
	
	public Quarto getQuartoAtual() {
		return quartoAtual;
	}

	public void setQuartoAtual(Quarto quartoAtual) {
		this.quartoAtual = quartoAtual;
	}

	public List<Quarto> getListaQuarto() {
		return listaQuarto;
	}

	public void setListaQuarto(List<Quarto> listaQuarto) {
		this.listaQuarto = listaQuarto;
	}
	
	public HtmlSelectOneMenu getSelectTipos() {
		return selectTipos;
	}

	public void setSelectTipos(HtmlSelectOneMenu selectTipos) {
		this.selectTipos = selectTipos;
	}

}
