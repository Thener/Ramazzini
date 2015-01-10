package br.com.ramazzini.model.empresa;


public enum UnidadeFederativa {

    ACRE("AC","Acre"), 
    ALAGOAS("AL", "Alagoas"),
    AMAZONAS("AM", "Amazonas"),
    AMAPA("AP", "Amapá"),
    BAHIA("BA", "Bahia"),
    CEARA("CE", "Ceará"),
    DISTRITO_FEDERAL("DF", "Distrito Federal"),
    ESPIRITO_SANTO("ES", "Espírto Santo"),
    GOIAS("GO", "Goiás"),
    MARANHAO("MA", "Maranhão"),
    MATO_GROSSO("MT", "Mato Grosso"),
    MATO_GROSSO_SUL("MS", "Mato Grosso do Sul"),
    MINAS_GERAIS("MG", "Minas Gerais"),
    PARA("PA", "Pará"),
    PARAIBA("PB", "Paraíba"),
    PARANA("PR", "Paraná"),
    PERNAMBUCO("PE", "Pernambuco"),
    PIAUI("PI", "Piauí"),
    RIO_DE_JANEIRO("RJ", "Rio de Janeiro"),
    RIO_GRANDE_NORTE("RN", "Rio Grande do Norte"),
    RIO_GRANDE_SUL("RS", "Rio Grande do Sul"),
    RONDONIA("RO", "Rondônia"),
    RORAIMA("RR", "Roraima"),
    SANTA_CATARINA("SC", "Santa Catarina"),
    SAO_PAULO("SP", "São Paulo"),
    SERGIPE("SE", "Sergipe"),
    TOCANTINS("TO", "Tocantins");

    private String value;
    private String descricao;

    UnidadeFederativa(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
    }    

    public String getValue() { return value; }

    public static UnidadeFederativa parse(String valor) {
    	UnidadeFederativa uf = null; // Default
        for (UnidadeFederativa item : UnidadeFederativa.values()) {
            if (item.getValue().equals(valor)) {
            	uf = item;
                break;
            }
        }
        return uf;
    }

	public String getDescricao() {
		return descricao;
	}

}