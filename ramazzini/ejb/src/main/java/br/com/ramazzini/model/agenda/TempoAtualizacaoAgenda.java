package br.com.ramazzini.model.agenda;

import java.util.ResourceBundle;


public enum TempoAtualizacaoAgenda {

    RAPIDO("5", "tempoAtualizacaoAgenda.rapido"),
    MEDIO("20", "tempoAtualizacaoAgenda.medio"),
    LONGO("60", "tempoAtualizacaoAgenda.longo");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    TempoAtualizacaoAgenda(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static TempoAtualizacaoAgenda parse(String valor) {
    	TempoAtualizacaoAgenda tempo = null; // Default
        for (TempoAtualizacaoAgenda item : TempoAtualizacaoAgenda.values()) {
            if (item.getValue().equals(valor)) {
            	tempo = item;
                break;
            }
        }
        return tempo;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}