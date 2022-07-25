package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class PrimefacesBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void definirData(SelectEvent<Date> event) {
		setData(event.getObject());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Date alterada", "A data agora é: " + format.format(data)));
	}
}
