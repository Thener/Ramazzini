package br.com.ramazzini.controller.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	    
    private ControleAcesso controleAcesso = new ControleAcesso();
    
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
}