package br.com.ramazzini.model.anamnese;

import java.util.ResourceBundle;


public enum Romberg {

	POSITIVO("POS","romberg.positivo"), 
    NEGATIVO("NEG", "romberg.negativo");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    Romberg(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static Romberg parse(String valor) {
    	Romberg sit = null; // Default
        for (Romberg item : Romberg.values()) {
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