package br.com.ramazzini.model.util;

import java.util.ResourceBundle;


public enum SimNao {

	SIM("1","simNao.Sim"),
    NAO("0","simNao.Nao");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SimNao(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SimNao parse(String valor) {
    	SimNao tipo = null; // Default
        for (SimNao item : SimNao.values()) {
            if (item.getValue().equals(valor)) {
            	tipo = item;
                break;
            }
        }
        return tipo;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}
}