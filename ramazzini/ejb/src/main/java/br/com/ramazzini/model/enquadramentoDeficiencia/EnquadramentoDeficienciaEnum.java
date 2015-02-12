package br.com.ramazzini.model.enquadramentoDeficiencia;

import java.util.ResourceBundle;

public enum EnquadramentoDeficienciaEnum {

	DEFICIENCIA_FISICA("DFIS","enquadramentoDeficiencia.deficienciaFisica"),
	DEFICIENCIA_AUDITIVA("DAUD","enquadramentoDeficiencia.deficienciaAuditiva"),
	DEFICIENCIA_VISUAL("DVIS","enquadramentoDeficiencia.deficienciaVisual"),
	DEFICIENCIA_MENTAL("DMEN","enquadramentoDeficiencia.deficienciaMental"),
	DEFICIENCIA_MULTIPLA("DMUL","enquadramentoDeficiencia.deficienciaMultipla");
	
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    EnquadramentoDeficienciaEnum(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static EnquadramentoDeficienciaEnum parse(String valor) {
    	EnquadramentoDeficienciaEnum sit = null; // Default
        for (EnquadramentoDeficienciaEnum item : EnquadramentoDeficienciaEnum.values()) {
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