package br.com.ramazzini.model.riscoOcupacional;


public enum TipoRiscoOcupacional {

    AUSENCIA_RISCO_OCUPACIONAL("AUS","Ausência de Risco Ocupacional"), 
    FISICO("FIS", "Físico"),
    QUIMICO("QUI", "Químico"),
    BIOLOGICO("BIO", "Biológico"),
    ERGONOMICO("ERG", "Ergonômico"),
    ACIDENTE("ACI", "Acidente"),
    MECANICO("MEC", "Mecânico");

    private String value;
    private String descricao;

    TipoRiscoOcupacional(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
    }    

    public String getValue() { return value; }

    public static TipoRiscoOcupacional parse(String valor) {
    	TipoRiscoOcupacional tipo = null; // Default
        for (TipoRiscoOcupacional item : TipoRiscoOcupacional.values()) {
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