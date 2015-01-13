package br.com.ramazzini.model.procedimento;

import java.util.ResourceBundle;


public enum TipoProcedimento {

	/* exemplo usando integer
	 * http://stackoverflow.com/questions/2751733/map-enum-in-jpa-with-fixed-values
	 */
    EXAME_COMPLEMENTAR("EXCOM","tipoProcedimento.exameComplementar"), 
    EXAME_CLINICO_OCUPACIONAL("EXCLI", "tipoProcedimento.exameClinicoOcupacional");

    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");
    
    private String value;
    private String chave;

    TipoProcedimento(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static TipoProcedimento parse(String valor) {
    	TipoProcedimento tipo = null; // Default
        for (TipoProcedimento item : TipoProcedimento.values()) {
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