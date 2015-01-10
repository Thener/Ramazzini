package br.com.ramazzini.model.horarioAtendimento;


public enum DiaSemana {

    DOMINGO("DOM","Domingo"), 
    SEGUNDA("SEG", "Segunda"),
    TERCA("TER", "Terça"),
    QUARTA("QUA", "Quarta"),
    QUINTA("QUI", "Quinta"),
    SEXTA("SEX", "Sexta"),
    SABADO("SAB", "Sábado");

    private String value;
    private String descricao;

    DiaSemana(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

}