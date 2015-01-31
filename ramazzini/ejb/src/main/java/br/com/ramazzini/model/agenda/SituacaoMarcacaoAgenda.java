package br.com.ramazzini.model.agenda;

import java.util.ResourceBundle;


public enum SituacaoMarcacaoAgenda {

    AGUARDANDO("AG", "situacaoMarcacaoAgenda.Aguardando"),
    EM_ATENDIMENTO("EA", "situacaoMarcacaoAgenda.EmAtendimento"),
    ATENDIDO("AT", "situacaoMarcacaoAgenda.Atendido"),
    NAO_COMPARECEU("NC", "situacaoMarcacaoAgenda.NaoCompareceu"),
    DESISTENCIA("DS", "situacaoMarcacaoAgenda.Desistencia");
    
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