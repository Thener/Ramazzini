package br.com.ramazzini.model.util;

import java.util.ResourceBundle;


public enum NaoSim {

	NAO("0","simNao.Nao", false),
	SIM("1","simNao.Sim", true);
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private boolean valueBoolean;
    private String chave;

    NaoSim(String value, String chave, boolean valueBoolean) { 
    	this.value = value;
    	this.valueBoolean = valueBoolean;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static NaoSim parse(String valor) {
    	NaoSim tipo = null; // Default
        for (NaoSim item : NaoSim.values()) {
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