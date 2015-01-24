package br.com.ramazzini.model.agenda;

import java.util.ResourceBundle;


public enum SituacaoMarcacaoAgenda {

    LIVRE("LI","situacaoMarcacaoAgenda.Livre"), 
    MARCADO("MA", "situacaoMarcacaoAgenda.Marcado"),
    AGUARDANDO("AG", "situacaoMarcacaoAgenda.Aguardando"),
    ATENDIDO("AT", "situacaoMarcacaoAgenda.Atendido"),
    SUSPENSO("SU", "situacaoMarcacaoAgenda.Suspenso"),
    NAO_COMPARECEU("NC", "situacaoMarcacaoAgenda.NaoCompareceu");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SituacaoMarcacaoAgenda(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SituacaoMarcacaoAgenda parse(String valor) {
    	SituacaoMarcacaoAgenda sit = null; // Default
        for (SituacaoMarcacaoAgenda item : SituacaoMarcacaoAgenda.values()) {
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