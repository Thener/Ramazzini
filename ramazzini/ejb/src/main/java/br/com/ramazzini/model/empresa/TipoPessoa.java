package br.com.ramazzini.model.empresa;


public enum TipoPessoa {

    PESSOA_JURIDICA("JUR","Pessoa Jurídica"), 
    PESSOA_FISICA("FIS", "Pessoa Física");

    private String value;
    private String descricao;

    TipoPessoa(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
    }    

    public String getValue() { return value; }

    public static TipoPessoa parse(String valor) {
    	TipoPessoa tipo = null; // Default
        for (TipoPessoa item : TipoPessoa.values()) {
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