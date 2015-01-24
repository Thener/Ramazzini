package br.com.ramazzini.model.parametro;

import java.util.ResourceBundle;


public enum ParametroSistema {

	//--- AGENDA:
    AGENDA_CRIAR_SABADO("AGENDA.CRIAR_SABADO","parametroSistema.agenda.CriarAgendaAosSabados"), 
    AGENDA_CRIAR_DOMINGO("AGENDA.CRIAR_DOMINGO", "parametroSistema.agenda.CriarAgendaAosDomingos"),
    AGENDA_CRIAR_FERIADO("AGENDA.CRIAR_FERIADO", "parametroSistema.agenda.CriarAgendaNosFeriados"),
    //---- CONTROLE DE ACESSO:
    ACESSO_NUM_MAX_TENTATIVAS_LOGIN("ACESSO.NUM_MAX_TENTATIVAS_LOGIN", "parametroSistema.acesso.NumeroMaxTentativasLogin");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    ParametroSistema(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

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