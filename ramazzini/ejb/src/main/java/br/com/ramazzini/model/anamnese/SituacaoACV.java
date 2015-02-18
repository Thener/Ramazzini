package br.com.ramazzini.model.anamnese;

import java.util.ResourceBundle;


public enum SituacaoACV {

	NDN("NDN","situacaoACV.ndn"), 
    ANORMAL("ANO", "situacaoACV.anormal");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SituacaoACV(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SituacaoACV parse(String valor) {
    	SituacaoACV sit = null; // Default
        for (SituacaoACV item : SituacaoACV.values()) {
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