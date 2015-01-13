package br.com.ramazzini.model.riscoOcupacional;

import java.util.ResourceBundle;


public enum TipoRiscoOcupacional {

    AUSENCIA_RISCO_OCUPACIONAL("AUS","tipoRiscoOcupacional.AusenciaRiscoOcupacional"), 
    FISICO("FIS", "tipoRiscoOcupacional.Fisico"),
    QUIMICO("QUI", "tipoRiscoOcupacional.Quimico"),
    BIOLOGICO("BIO", "tipoRiscoOcupacional.Biologico"),
    ERGONOMICO("ERG", "tipoRiscoOcupacional.Ergonomico"),
    ACIDENTE("ACI", "tipoRiscoOcupacional.Acidente"),
    MECANICO("MEC", "tipoRiscoOcupacional.Mecanico");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    TipoRiscoOcupacional(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
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

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}
}