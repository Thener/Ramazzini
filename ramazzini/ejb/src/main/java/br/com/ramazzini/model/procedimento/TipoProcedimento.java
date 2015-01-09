package br.com.ramazzini.model.procedimento;


public enum TipoProcedimento {

	EXAME_CLINICO_OCUPACIONAL("EXCLIN"),
	EXAME_COMPLEMENTAR("EXCOMP");
	
    private String dbValue;

    TipoProcedimento(String dbValue) {
        this.dbValue = dbValue;
    }

	public static TipoProcedimento fromValue(String value) {

		for (TipoProcedimento tp : TipoProcedimento.values()) {
			if (tp.dbValue.equals(value)) {
				return tp;
			}
		}

		throw new IllegalArgumentException("Tipo Procedimento inválido: " + value);
	}

	public String getDbValue() {
		return dbValue;
	}    
	
	public String toValue() {
		return dbValue;
	}	

}