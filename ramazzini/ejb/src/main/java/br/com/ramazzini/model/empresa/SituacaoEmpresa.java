package br.com.ramazzini.model.empresa;


public enum SituacaoEmpresa {

    ATIVA("ATI","Ativa"), 
    CANCELADA("CAN", "Cancelada"),
    SUSPENSA("SUS", "Suspensa");

    private String value;
    private String descricao;

    SituacaoEmpresa(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
    }    

    public String getValue() { return value; }

    public static SituacaoEmpresa parse(String valor) {
    	SituacaoEmpresa situacao = null; // Default
        for (SituacaoEmpresa item : SituacaoEmpresa.values()) {
            if (item.getValue().equals(valor)) {
            	situacao = item;
                break;
            }
        }
        return situacao;
    }

	public String getDescricao() {
		return descricao;
	}

}