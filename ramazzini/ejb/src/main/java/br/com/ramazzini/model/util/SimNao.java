package br.com.ramazzini.model.util;

import java.util.ResourceBundle;


public enum SimNao {

	SIM("1","simNao.Sim", true),
    NAO("0","simNao.Nao", false);
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private boolean valueBoolean;
    private String chave;

    SimNao(String value, String chave, boolean valueBoolean) { 
    	this.value = value;
    	this.valueBoolean = valueBoolean;
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
    
	public boolean isValueBoolean() {
		return valueBoolean;
	}

	public String getStringChave() {
		return bundle.getString(chave);
	}
}