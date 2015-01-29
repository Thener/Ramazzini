package br.com.ramazzini.controller.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conversation conversation;
	    
    private ControleAcesso controleAcesso = new ControleAcesso();
    
	private static ResourceBundle bundle;
    
    private String uriRequisicao;
    
    private boolean somenteLeitura = Boolean.FALSE;
    
	public ControleAcesso getControleAcesso() {
		return controleAcesso;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}
	
	public String getUriRequisicao() {
		return uriRequisicao;
	}

	public void setUriRequisicao(String uriRequisicao) {
		this.uriRequisicao = uriRequisicao;
	}	
	
    public String getFormattedTime(Date time, String format) {  
        
    	if (time == null) {  
            return null;  
        }  
  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);  
        return simpleDateFormat.format(time);  
    }	
    
    private static ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msgs");
		}
		return bundle;
	}
    
    protected static String getValorChaveMsg(String chave) {
		return getBundle().getString(chave);
	}
    
	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}
	
	public String getConversationId() {
		beginConversation();
		return conversation.getId();
	}
}