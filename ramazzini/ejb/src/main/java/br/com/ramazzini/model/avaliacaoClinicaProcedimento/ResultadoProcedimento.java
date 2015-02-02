package br.com.ramazzini.model.avaliacaoClinicaProcedimento;

import java.util.ResourceBundle;


public enum ResultadoProcedimento {

	NORMAL("NOR","resultadoProcedimento.Normal"), 
    ALTERACAO("ALT","resultadoProcedimento.Alteracao"), 
    ALTERACAO_ESTAVEL("AES", "resultadoProcedimento.AlteracaoEstavel"),
    ALTERACAO_AGRAVAMENTO("AAG", "resultadoProcedimento.AlteracaoAgravamento");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    ResultadoProcedimento(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static ResultadoProcedimento parse(String valor) {
    	ResultadoProcedimento sit = null; // Default
        for (ResultadoProcedimento item : ResultadoProcedimento.values()) {
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