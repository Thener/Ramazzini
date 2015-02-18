package br.com.ramazzini.model.agenda;

import java.util.ResourceBundle;


public enum StatusAtualizacaoAgenda {

    LIGADO("1", "statusAtualizacaoAgenda.ligado"),
    DESLIGADO("0", "statusAtualizacaoAgenda.desligado");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    StatusAtualizacaoAgenda(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static StatusAtualizacaoAgenda parse(String valor) {
    	StatusAtualizacaoAgenda status = null; // Default
        for (StatusAtualizacaoAgenda item : StatusAtualizacaoAgenda.values()) {
            if (item.getValue().equals(valor)) {
            	status = item;
                break;
            }
        }
        return status;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}