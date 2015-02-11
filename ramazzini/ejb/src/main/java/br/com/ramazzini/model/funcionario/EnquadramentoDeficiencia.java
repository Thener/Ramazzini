package br.com.ramazzini.model.funcionario;

import java.util.ResourceBundle;

public enum EnquadramentoDeficiencia {

	DEFICIENCIA_FISICA("DFIS","enquadramentoDeficiencia.deficienciaFisica"),
	DEFICIENCIA_AUDITIVA("DAUD","enquadramentoDeficiencia.deficienciaAuditiva"),
	DEFICIENCIA_VISUAL("DVIS","enquadramentoDeficiencia.deficienciaVisual"),
	DEFICIENCIA_MENTAL("DMEN","enquadramentoDeficiencia.deficienciaMental"),
	DEFICIENCIA_MULTIPLA("DMUL","enquadramentoDeficiencia.deficienciaMultipla");
	
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    EnquadramentoDeficiencia(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static EnquadramentoDeficiencia parse(String valor) {
    	EnquadramentoDeficiencia sit = null; // Default
        for (EnquadramentoDeficiencia item : EnquadramentoDeficiencia.values()) {
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