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
		
	public static void mensagemErroPorChave(String chave) {
		mensagemErro(getValor(chave));
	}
	
	public static void mensagemErroPorChave(String chave, String... parameters) {
		int i = 0;
		String parametro;
		String mensagem = getValor(chave);
		for (String p : parameters) {
			parametro = "{"+i+"}";
			mensagem = mensagem.replace(parametro, getValor(p));
			i++;
		}
		mensagemErro(mensagem);
	}	
	
	public static void mensagemInformacao(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,"");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void mensagemInformacaoPorChave(String chave) {
		mensagemInformacao(getValor(chave));
	}
	
	public static void mensagemInformacaoPorChave(String chave, String... parameters) {
		int i = 0;
		String parametro;
		String mensagem = getValor(chave);
		for (String p : parameters) {
			parametro = "{"+i+"}";
			mensagem = mensagem.replace(parametro, getValor(p));
			i++;
		}
		mensagemInformacao(mensagem);
	}	
	
	public static void mensagemErroAposRedirect(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public static void mensagemErroPorChaveAposRedirect(String chave) {
		mensagemErroAposRedirect(getValor(chave));
	}
	
	public static void mensagemErroPorChaveAposRedirect(String chave, String... parameters) {
		int i = 0;
		String parametro;
		String mensagem = getValor(chave);
		for (String p : parameters) {
			parametro = "{"+i+"}";
			mensagem = mensagem.replace(parametro, getValor(p));
			i++;
		}
		mensagemErroAposRedirect(mensagem);
	}	
	
	public static void mensagemInformacaoAposRedirect(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public static void mensagemInformacaoPorChaveAposRedirect(String chave) {
		mensagemInformacaoAposRedirect(getValor(chave));
	}
	
	public static void mensagemInformacaoPorChaveAposRedirect(String chave, String... parameters) {
		int i = 0;
		String parametro;
		String mensagem = getValor(chave);
		for (String p : parameters) {
			parametro = "{"+i+"}";
			mensagem = mensagem.replace(parametro, getValor(p));
			i++;
		}
		mensagemInformacaoAposRedirect(mensagem);
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
