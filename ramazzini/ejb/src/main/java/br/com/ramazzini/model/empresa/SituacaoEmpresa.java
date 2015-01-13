package br.com.ramazzini.model.empresa;

import java.util.ResourceBundle;


public enum SituacaoEmpresa {

    ATIVA("ATI","situacaoEmpresa.ativa"), 
    CANCELADA("CAN", "situacaoEmpresa.cancelada"),
    SUSPENSA("SUS", "situacaoEmpresa.suspensa");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SituacaoEmpresa(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SituacaoEmpresa parse(String valor) {
    	SituacaoEmpresa situacao = null; // Default
        for (SituacaoEmpresa item : SituacaoEmpresa.values()) {
            if (item.getValue().equals(valor)) {
            	situacao = item;
                break;
            }
        }
        return situacao;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}