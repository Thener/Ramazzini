package br.com.ramazzini.model.funcionario;


public enum SituacaoFuncionario {

    ATIVO("AT","Ativo"), 
    DEMITIDO("DE", "Demitido"),
    LICENCA_MATERNIDADE("LM", "Licença Maternidade"),
    AFASTADO("AF", "Afastado"),
    LICENCIADO("LI", "Licenciado"),
    BAIXA("BA", "Baixa");

    private String value;
    private String descricao;

    SituacaoFuncionario(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

}