package br.com.ramazzini.model.funcionario;

import java.util.ResourceBundle;


public enum Sexo {

    MASCULINO("M","sexo.Masculino"), 
    FEMININO("F", "sexo.Feminino");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    Sexo(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static Sexo parse(String valor) {
    	Sexo sexo = null; // Default
        for (Sexo item : Sexo.values()) {
            if (item.getValue().equals(valor)) {
            	sexo = item;
                break;
            }
        }
        return sexo;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}