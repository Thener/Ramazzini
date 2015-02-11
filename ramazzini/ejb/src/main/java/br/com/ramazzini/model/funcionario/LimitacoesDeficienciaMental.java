package br.com.ramazzini.model.funcionario;

import java.util.ResourceBundle;

public enum LimitacoesDeficienciaMental {

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

    LimitacoesDeficienciaMental(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static LimitacoesDeficienciaMental parse(String valor) {
    	LimitacoesDeficienciaMental sit = null; // Default
        for (LimitacoesDeficienciaMental item : LimitacoesDeficienciaMental.values()) {
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