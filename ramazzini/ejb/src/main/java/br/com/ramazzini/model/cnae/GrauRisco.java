package br.com.ramazzini.model.cnae;

import java.util.ResourceBundle;


public enum GrauRisco {

    RISCO_1(1,"grauRisco.grauRisco1"), 
    RISCO_2(2, "grauRisco.grauRisco2"),
    RISCO_3(3, "grauRisco.grauRisco3"),
    RISCO_4(4, "grauRisco.grauRisco4");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private Integer value;
    private String chave;

    GrauRisco(Integer value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public Integer getValue() { return value; }

    public static GrauRisco parse(Integer valor) {
    	GrauRisco grau = null; // Default
        for (GrauRisco item : GrauRisco.values()) {
            if (item.getValue() == valor) {
            	grau = item;
                break;
            }
        }
        return grau;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}