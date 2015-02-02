package br.com.ramazzini.model.avaliacaoClinicaProcedimento;

import java.util.ResourceBundle;


public enum TipoAlteracaoProcedimento {

	OCUPACIONAL("OCU","tipoAlteracaoProcedimento.Ocupacional"), 
    NAO_OCUPACIONAL("NOC","tipoAlteracaoProcedimento.NaoOcupacional"), 
    OCUPACIONAL_NAO_OCUPACIONAL("ONO", "tipoAlteracaoProcedimento.OcupacionalNaoOcupacional"),
    INDETERMINADO("IND", "tipoAlteracaoProcedimento.Indeterminado");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    TipoAlteracaoProcedimento(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static TipoAlteracaoProcedimento parse(String valor) {
    	TipoAlteracaoProcedimento sit = null; // Default
        for (TipoAlteracaoProcedimento item : TipoAlteracaoProcedimento.values()) {
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