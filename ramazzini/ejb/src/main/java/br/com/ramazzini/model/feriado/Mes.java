package br.com.ramazzini.model.feriado;

import java.util.ResourceBundle;


public enum Mes {

    JANEIRO(1,"mes.janeiro"), 
    FEVEREIRO(2, "mes.fevereiro"),
    MARCO(3, "mes.marco"),
    ABRIL(4, "mes.abril"),
    MAIO(5, "mes.maio"),
    JUNHO(6, "mes.junho"),
    JULHO(7, "mes.julho"),
    AGOSTO(8, "mes.agosto"),
    SETEMBRO(9, "mes.setembro"),
    OUTUBRO(10, "mes.outubro"),
    NOVEMBRO(11,"mes.novembro"), 
    DEZEMBRO(12, "mes.dezembro");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private Integer value;
    private String chave;

    Mes(Integer value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public Integer getValue() { return value; }

    public static Mes parse(Integer valor) {
    	Mes mes = null; // Default
        for (Mes item : Mes.values()) {
            if (item.getValue() == valor) {
            	mes = item;
                break;
            }
        }
        return mes;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}