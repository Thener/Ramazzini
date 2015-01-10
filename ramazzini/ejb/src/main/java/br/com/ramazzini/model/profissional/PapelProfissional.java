package br.com.ramazzini.model.profissional;


public enum PapelProfissional {

    MEDICO("MED","Médico(a)"), 
    ENGENHEIRO("ENG", "Engenheiro(a)"),
    FONOAUDIOLOGO("FON", "Fonoaudiólogo(a)"),
    PSICOLOGO("PSI", "Psicólogo(a)"),
    TECNICO_SEG_TRABALHO("TST", "Técnico(a) Segurança do Trabalho"),
    TECNICO("TEC", "Técnico(a)");

    private String value;
    private String descricao;

    PapelProfissional(String value, String descricao) { 
    	this.value = value;
    	this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

}