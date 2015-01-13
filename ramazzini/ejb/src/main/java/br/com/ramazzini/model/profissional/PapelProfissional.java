package br.com.ramazzini.model.profissional;

import java.util.ResourceBundle;


public enum PapelProfissional {

    MEDICO("MED","papelProfissional.Medico"), 
    ENGENHEIRO("ENG", "papelProfissional.Engenheiro"),
    FONOAUDIOLOGO("FON", "papelProfissional.Fonoaudiologo"),
    PSICOLOGO("PSI", "papelProfissional.Psicologo"),
    TECNICO_SEG_TRABALHO("TST", "papelProfissional.TecnicoSegurancaTrabalho"),
    TECNICO("TEC", "papelProfissional.Tecnico");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private String value;
    private String chave;

    PapelProfissional(String value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public String getValue() { return value; }

    public static PapelProfissional parse(String valor) {
    	PapelProfissional papel = null; // Default
        for (PapelProfissional item : PapelProfissional.values()) {
            if (item.getValue().equals(valor)) {
            	papel = item;
                break;
            }
        }
        return papel;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}