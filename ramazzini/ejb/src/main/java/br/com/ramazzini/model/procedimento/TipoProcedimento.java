package br.com.ramazzini.model.procedimento;


public enum TipoProcedimento {

	/* exemplo usando integer
	 * http://stackoverflow.com/questions/2751733/map-enum-in-jpa-with-fixed-values
	 */
    EXAME_COMPLEMENTAR("EXCOM","Exame Complementar"), 
    EXAME_CLINICO_OCUPACIONAL("EXCLI", "Exame Clínico Ocupacional");

    private String value;
    private String descricao;

    TipoProcedimento(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

}