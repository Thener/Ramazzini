package br.com.ramazzini.model.anamnese;

import java.util.ResourceBundle;


public enum SituacaoAR {

	NDN("NDN","situacaoAR.ndn"), 
    ANORMAL("ANO", "situacaoAR.anormal");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SituacaoAR(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SituacaoAR parse(String valor) {
    	SituacaoAR sit = null; // Default
        for (SituacaoAR item : SituacaoAR.values()) {
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