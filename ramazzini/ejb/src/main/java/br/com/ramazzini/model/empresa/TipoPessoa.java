package br.com.ramazzini.model.empresa;

import java.util.ResourceBundle;


public enum TipoPessoa {

    PESSOA_JURIDICA("JUR","tipoPessoa.pessoaJuridica"), 
    PESSOA_FISICA("FIS", "tipoPessoa.pessoaFisica");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    TipoPessoa(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static TipoPessoa parse(String valor) {
    	TipoPessoa tipo = null; // Default
        for (TipoPessoa item : TipoPessoa.values()) {
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