package br.com.ramazzini.model.funcionario;

import java.util.ResourceBundle;


public enum OrigemDeficienciaEnum {

	ACIDENTE_TRABALHO("AT","origemDeficiencia.acidenteTrabalho"),
	CONGENITA("CG","origemDeficiencia.congenita"),
	POS_OPERATORIA("PO","origemDeficiencia.posOperatorio"),
	ACIDENTE_COMUM("AC","origemDeficiencia.acidenteComum"),
	DOENCA("DC","origemDeficiencia.doenca");
	
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    OrigemDeficienciaEnum(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static OrigemDeficienciaEnum parse(String valor) {
    	OrigemDeficienciaEnum sit = null; // Default
        for (OrigemDeficienciaEnum item : OrigemDeficienciaEnum.values()) {
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