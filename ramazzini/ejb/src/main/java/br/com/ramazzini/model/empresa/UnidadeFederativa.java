package br.com.ramazzini.model.empresa;

import java.util.ResourceBundle;


public enum UnidadeFederativa {

    ACRE("AC","unidadeFederativa.Acre"), 
    ALAGOAS("AL", "unidadeFederativa.Alagoas"),
    AMAZONAS("AM", "unidadeFederativa.Amazonas"),
    AMAPA("AP", "unidadeFederativa.Amapa"),
    BAHIA("BA", "unidadeFederativa.Bahia"),
    CEARA("CE", "unidadeFederativa.Ceara"),
    DISTRITO_FEDERAL("DF", "unidadeFederativa.DistritoFederal"),
    ESPIRITO_SANTO("ES", "unidadeFederativa.EspirtoSanto"),
    GOIAS("GO", "unidadeFederativa.Goias"),
    MARANHAO("MA", "unidadeFederativa.Maranhao"),
    MATO_GROSSO("MT", "unidadeFederativa.MatoGrosso"),
    MATO_GROSSO_SUL("MS", "unidadeFederativa.MatoGrossoSul"),
    MINAS_GERAIS("MG", "unidadeFederativa.MinasGerais"),
    PARA("PA", "unidadeFederativa.Para"),
    PARAIBA("PB", "unidadeFederativa.Paraiba"),
    PARANA("PR", "unidadeFederativa.Parana"),
    PERNAMBUCO("PE", "unidadeFederativa.Pernambuco"),
    PIAUI("PI", "unidadeFederativa.Piaui"),
    RIO_DE_JANEIRO("RJ", "unidadeFederativa.RioDeJaneiro"),
    RIO_GRANDE_NORTE("RN", "unidadeFederativa.RioGrandeNorte"),
    RIO_GRANDE_SUL("RS", "unidadeFederativa.RioGrandeSul"),
    RONDONIA("RO", "unidadeFederativa.Rondonia"),
    RORAIMA("RR", "unidadeFederativa.Roraima"),
    SANTA_CATARINA("SC", "unidadeFederativa.SantaCatarina"),
    SAO_PAULO("SP", "unidadeFederativa.SaoPaulo"),
    SERGIPE("SE", "unidadeFederativa.Sergipe"),
    TOCANTINS("TO", "unidadeFederativa.Tocantins");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    UnidadeFederativa(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
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

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}