package br.com.ramazzini.model.empresa;


public enum TipoPcmso {

    COM_COORDENCAO("COM","Pcmso com coordenação"), 
    SEM_COORDENACAO("SEM", "Pcmso sem coordenação");

    private String value;
    private String descricao;

    TipoPcmso(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
    }    

    public String getValue() { return value; }

    public static TipoPcmso parse(String valor) {
    	TipoPcmso tipo = null; // Default
        for (TipoPcmso item : TipoPcmso.values()) {
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