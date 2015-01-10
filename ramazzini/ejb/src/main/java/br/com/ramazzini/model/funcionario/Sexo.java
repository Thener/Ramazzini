package br.com.ramazzini.model.funcionario;


public enum Sexo {

    MASCULINO("M","Masculino"), 
    FEMININO("F", "Feminino");

    private String value;
    private String descricao;

    Sexo(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
    }    

    public String getValue() { return value; }

    public static Sexo parse(String valor) {
    	Sexo sexo = null; // Default
        for (Sexo item : Sexo.values()) {
            if (item.getValue().equals(valor)) {
            	sexo = item;
                break;
            }
        }
        return sexo;
    }

	public String getDescricao() {
		return descricao;
	}

}