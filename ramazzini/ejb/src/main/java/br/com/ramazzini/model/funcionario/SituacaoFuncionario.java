package br.com.ramazzini.model.funcionario;

import java.util.ResourceBundle;


public enum SituacaoFuncionario {

	AGENDADO("AG","situacaoFuncionario.Agendado"), 
    ATIVO("AT","situacaoFuncionario.Ativo"), 
    DEMITIDO("DE", "situacaoFuncionario.Demitido"),
    LICENCA_MATERNIDADE("LM", "situacaoFuncionario.LicencaMaternidade"),
    AFASTADO("AF", "situacaoFuncionario.Afastado"),
    LICENCIADO("LI", "situacaoFuncionario.Licenciado"),
    BAIXA("BA", "situacaoFuncionario.Baixa");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    SituacaoFuncionario(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static SituacaoFuncionario parse(String valor) {
    	SituacaoFuncionario sit = null; // Default
        for (SituacaoFuncionario item : SituacaoFuncionario.values()) {
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