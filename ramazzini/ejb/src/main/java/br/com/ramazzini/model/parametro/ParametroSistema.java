package br.com.ramazzini.model.parametro;

import java.util.ResourceBundle;


public enum ParametroSistema {

	//--- AGENDA:
    AGENDA_CRIAR_SABADO("AGENDA.CRIAR_SABADO","parametroSistema.agenda.CriarAgendaAosSabados", "0"), 
    AGENDA_CRIAR_DOMINGO("AGENDA.CRIAR_DOMINGO", "parametroSistema.agenda.CriarAgendaAosDomingos", "0"),
    AGENDA_CRIAR_FERIADO("AGENDA.CRIAR_FERIADO", "parametroSistema.agenda.CriarAgendaNosFeriados", "0"),
    //---- CONTROLE DE ACESSO:
    ACESSO_NUM_MAX_TENTATIVAS_LOGIN("ACESSO.NUM_MAX_TENTATIVAS_LOGIN", "parametroSistema.acesso.NumeroMaxTentativasLogin", "3"),
    
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