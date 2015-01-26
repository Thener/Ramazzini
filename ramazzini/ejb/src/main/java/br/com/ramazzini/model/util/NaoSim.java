package br.com.ramazzini.model.util;

import java.util.ResourceBundle;


public enum NaoSim {

	NAO("0","simNao.Nao"),
	SIM("1","simNao.Sim");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    NaoSim(String value, String chave) { 
    	this.value = value;
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
    
	public String getStringChave() {
		return bundle.getString(chave);
	}
}