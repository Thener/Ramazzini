package br.com.ramazzini.model.empresa;

import java.util.ResourceBundle;


public enum TipoPcmso {

    COM_COORDENCAO("COM","tipoPcmso.pcmsoComCoordenacao"), 
    SEM_COORDENACAO("SEM", "tipoPcmso.pcmsoSemCoordenacao");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    TipoPcmso(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static TipoPcmso parse(String valor) {
    	TipoPcmso tipo = null; // Default
        for (TipoPcmso item : TipoPcmso.values()) {
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