package br.com.ramazzini.model.avaliacaoClinica;

import java.util.ResourceBundle;


public enum SituacaoAvaliacaoClinica {

	EM_ANDAMENTO("AND","situacaoAvaliacaoClinica.EmAndamento"),
	APTO("APT","situacaoAvaliacaoClinica.Apto"), 
    APTO_COM_RESTRICAO("ACR","situacaoAvaliacaoClinica.AptoComRestricao"), 
    APTO_INCLUINDO_TRABALHO_ALTURA("AIT", "situacaoAvaliacaoClinica.AptoIncluindoTrabAltura"),
    INAPTO("INA", "situacaoAvaliacaoClinica.Inapto"),
    AGUARDANDO_EXAMES("AEX", "situacaoAvaliacaoClinica.AguardandoExames"),
    AGUARDANDO_PARECER("APA", "situacaoAvaliacaoClinica.AguardandoParecer"),
    NAO_CONCLUIDO("NCO", "situacaoAvaliacaoClinica.NaoConcluido");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SituacaoAvaliacaoClinica(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SituacaoAvaliacaoClinica parse(String valor) {
    	SituacaoAvaliacaoClinica sit = null; // Default
        for (SituacaoAvaliacaoClinica item : SituacaoAvaliacaoClinica.values()) {
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