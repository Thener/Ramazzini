package br.com.ramazzini.model.guia;

import java.util.ResourceBundle;


public enum SituacaoGuia {

	EMITIDA("EM","situacaoGuia.Emitida"), 
    CANCELADA("CA", "situacaoGuia.Cancelada");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SituacaoGuia(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SituacaoGuia parse(String valor) {
    	SituacaoGuia sit = null; // Default
        for (SituacaoGuia item : SituacaoGuia.values()) {
            if (item.getValue().equals(valor)) {
            	sit = item;
                break;
            }
        }
        return sit;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}