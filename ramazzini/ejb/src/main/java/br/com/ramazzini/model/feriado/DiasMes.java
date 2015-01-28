package br.com.ramazzini.model.feriado;

import java.util.ResourceBundle;


public enum DiasMes {

    DIA_1(1,"diaMes.1"), 
    DIA_2(2, "diaMes.2"),
    DIA_3(3, "diaMes.3"),
    DIA_4(4, "diaMes.4"),
    DIA_5(5, "diaMes.5"),
    DIA_6(6, "diaMes.6"),
    DIA_7(7, "diaMes.7"),
    DIA_8(8, "diaMes.8"),
    DIA_9(9, "diaMes.9"),
    DIA_10(10, "diaMes.10"),
    DIA_11(11,"diaMes.11"), 
    DIA_12(12, "diaMes.12"),
    DIA_13(13, "diaMes.13"),
    DIA_14(14, "diaMes.14"),
    DIA_15(15, "diaMes.15"),
    DIA_16(16, "diaMes.16"),
    DIA_17(17, "diaMes.17"),
    DIA_18(18, "diaMes.18"),
    DIA_19(19, "diaMes.19"),
    DIA_20(20, "diaMes.20"),
    DIA_21(21,"diaMes.21"), 
    DIA_22(22, "diaMes.22"),
    DIA_23(23, "diaMes.23"),
    DIA_24(24, "diaMes.24"),
    DIA_25(25, "diaMes.25"),
    DIA_26(26, "diaMes.26"),
    DIA_27(27, "diaMes.27"),
    DIA_28(28, "diaMes.28"),
    DIA_29(29, "diaMes.29"),
    DIA_30(30, "diaMes.30"),
    DIA_31(31, "diaMes.31");
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    private Integer value;
    private String chave;

    DiasMes(Integer value, String chave) { 
    	this.value = value;
    	this.chave = chave;
    }    

    public Integer getValue() { return value; }

    public static DiasMes parse(Integer valor) {
    	DiasMes dia = null; // Default
        for (DiasMes item : DiasMes.values()) {
            if (item.getValue() == valor) {
            	dia = item;
                break;
            }
        }
        return dia;
    }

	public String getChave() {
		return chave;
	}    
    
	public String getStringChave() {
		return bundle.getString(chave);
	}

}