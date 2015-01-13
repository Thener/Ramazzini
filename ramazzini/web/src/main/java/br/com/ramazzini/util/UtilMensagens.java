package br.com.ramazzini.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UtilMensagens {
	
	private static ResourceBundle bundle;
	
	public static void mensagemErro(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,"");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void mensagemInformacao(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,"");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void mensagemErroPorChave(String chave) {
		mensagemErro(getValor(chave));
	}
	
	public static void mensagemInformacaoPorChave(String chave) {
		mensagemInformacao(getValor(chave));
	}
	
	private static String getValor(String chave) {
		return getBundle().getString(chave);
	}
	
	private static ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msgs");
		}
		return bundle;
	}

}
