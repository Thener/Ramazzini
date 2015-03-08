package br.com.ramazzini.model.procedimento;

import java.util.ResourceBundle;


public enum TipoExameClinico {

	/* exemplo usando integer
	 * http://stackoverflow.com/questions/2751733/map-enum-in-jpa-with-fixed-values
	 */
    ADMISSIONAL("ADM","tipoExameClinico.admissional"), 
    PERIODICO("PER", "tipoExameClinico.Periodico"),
    MUDANCA_FUNCAO("MUD", "tipoExameClinico.MudancaFuncao"),
    RETORNO_TRABALHO("RET", "tipoExameClinico.RetornoTrabalho"),
    DEMISSIONAL("DEM", "tipoExameClinico.Demissional"),
    RET_TRAB_MUD_FUNCAO("RMF", "tipoExameClinico.RetornoTrabalhoComMudancaFuncao"),
    OUTROS("OUT", "tipoExameClinico.Outros");

    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");
    
    private String value;
    private String chave;

    TipoExameClinico(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static TipoExameClinico parse(String valor) {
    	TipoExameClinico tipo = null; // Default
        for (TipoExameClinico item : TipoExameClinico.values()) {
            if (item.getValue().equals(valor)) {
            	tipo = item;
                break;
            }
        }
        return tipo;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}
	
}