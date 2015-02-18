package br.com.ramazzini.model.agenda;

import java.util.ResourceBundle;


public enum StatusNotificacaoAgenda {

    LIGADO("1", "statusNotificacaoAgenda.ligado"),
    DESLIGADO("0", "statusNotificacaoAgenda.desligado");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    StatusNotificacaoAgenda(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static StatusNotificacaoAgenda parse(String valor) {
    	StatusNotificacaoAgenda status = null; // Default
        for (StatusNotificacaoAgenda item : StatusNotificacaoAgenda.values()) {
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