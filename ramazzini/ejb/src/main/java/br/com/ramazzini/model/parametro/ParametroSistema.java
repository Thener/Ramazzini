package br.com.ramazzini.model.parametro;

import java.util.ResourceBundle;


public enum ParametroSistema {

    AGENDA_CRIAR_SABADO("AGENDA.CRIAR_SABADO","parametroSistema.CriarAgendaAosSabados"), 
    AGENDA_CRIAR_DOMINGO("AGENDA.CRIAR_DOMINGO", "parametroSistema.CriarAgendaAosDomingos"),
    AGENDA_CRIAR_FERIADO("AGENDA.CRIAR_FERIADO", "parametroSistema.CriarAgendaNosFeriados");
    
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