package br.com.ramazzini.model.parametro;

import java.util.ResourceBundle;


public enum ParametroSistema {

	//--- AGENDA:
    AGENDA_TEMPO_ATUALIZACAO_AUTOMATICA("AGENDA.TEMPO_ATUALIZACAO_AUTOMATICA","parametroSistema.agenda.tempoAtualizacaoAutomatica", "20"), 
    //---- CONTROLE DE ACESSO:
    ACESSO_NUM_MAX_TENTATIVAS_LOGIN("ACESSO.NUM_MAX_TENTATIVAS_LOGIN", "parametroSistema.acesso.numeroMaxTentativasLogin", "3"),
    //---- LOCAL RELATÓRIOS
    DIR_BASE_RELATORIO("RELATORIO.DIR_BASE_RELATORIO", "parametroSistema.relatorio.diretorioBase", "4");

    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;
    private String valorDefault;

    ParametroSistema(String value, String chave, String valorDefault) { 
    	this.value = value;
    	this.chave = chave;
    	this.valorDefault = valorDefault;
    }    

    public String getValue() { return value; }
    
    public String getValorDefault() {return valorDefault; }

    public static ParametroSistema parse(String valor) {
    	ParametroSistema param = null; // Default
        for (ParametroSistema item : ParametroSistema.values()) {
            if (item.getValue().equals(valor)) {
            	param = item;
                break;
            }
        }
        return param;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}