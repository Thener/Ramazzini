package br.com.ramazzini.model.horarioAtendimento;

import java.util.ResourceBundle;


public enum DiaSemana {

    DOMINGO("DOM","diaSemana.Domingo"), 
    SEGUNDA("SEG", "diaSemana.Segunda"),
    TERCA("TER", "diaSemana.Terca"),
    QUARTA("QUA", "diaSemana.Quarta"),
    QUINTA("QUI", "diaSemana.Quinta"),
    SEXTA("SEX", "diaSemana.Sexta"),
    SABADO("SAB", "diaSemana.Sabado");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    DiaSemana(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static DiaSemana parse(String valor) {
    	DiaSemana dia = null; // Default
        for (DiaSemana item : DiaSemana.values()) {
            if (item.getValue().equals(valor)) {
            	dia = item;
                break;
            }
        }
        return dia;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}