package br.com.ramazzini.model.cnae;


public enum GrauRisco {

    RISCO_1(1,"Grau de risco 1"), 
    RISCO_2(2, "Grau de risco 2"),
    RISCO_3(3, "Grau de risco 3"),
    RISCO_4(5, "Grau de risco 4");

    private Integer value;
    private String descricao;

    GrauRisco(Integer value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
    }    

    public Integer getValue() { return value; }

    public static GrauRisco parse(Integer valor) {
    	GrauRisco grau = null; // Default
        for (GrauRisco item : GrauRisco.values()) {
            if (item.getValue() == valor) {
            	grau = item;
                break;
            }
        }
        return grau;
    }

	public String getDescricao() {
		return descricao;
	}

}