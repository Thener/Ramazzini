package br.com.ramazzini.model.limitacoesDeficienciaMental;

import java.util.ResourceBundle;

public enum LimitacoesDeficienciaMentalEnum {

	COMUNICACAO("CO","limitacoesDeficienciaMental.comunicacao"),
	CUIDADO_PESSOAL("CP","limitacoesDeficienciaMental.cuidadoPessoal"),
	HABILIDADES_SOCIAIS("HP","limitacoesDeficienciaMental.habilidadesSociais"),
	UTILIZACAO_RECURSOS_COMUNIDADE("UR","limitacoesDeficienciaMental.utilizacaoRecursosComunidade"),
	SAUDE_SEGURANCA("SS","limitacoesDeficienciaMental.saudeSeguranca"),
	HABILIDADES_ACADEMICAS("HA","limitacoesDeficienciaMental.habilidadesAcademicas"),
	LAZER("LA","limitacoesDeficienciaMental.lazer"),
	TRABALHO("TB","limitacoesDeficienciaMental.trabalho");

	private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    LimitacoesDeficienciaMentalEnum(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static LimitacoesDeficienciaMentalEnum parse(String valor) {
    	LimitacoesDeficienciaMentalEnum sit = null; // Default
        for (LimitacoesDeficienciaMentalEnum item : LimitacoesDeficienciaMentalEnum.values()) {
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