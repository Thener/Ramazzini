package br.com.ramazzini.model.anamnese;

import java.util.ResourceBundle;


public enum TipoHabito {

	NENHUM("NE","tipoHabito.Nenhum"), 
    TABAGISMO("TA","tipoHabito.tabagismo"), 
    ETILISMO("ET", "tipoHabito.etilismo"),
    TABAGISMO_ETILISMO_SOCIAL("TE", "tipoHabito.tabagismoEtilismoSocial"),
    OUTROS("OU", "tipoHabito.outros");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    TipoHabito(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static TipoHabito parse(String valor) {
    	TipoHabito sit = null; // Default
        for (TipoHabito item : TipoHabito.values()) {
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