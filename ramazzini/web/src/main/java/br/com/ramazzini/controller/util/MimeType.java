package br.com.ramazzini.controller.util;

public enum MimeType {

	// Mais utilizados
	APPLICATION_PDF_PDF("application/pdf", ".pdf"),
	APPLICATION_ZIP_ZIP("application/zip", ".zip"),
	TEXT_CSV_CSV("text/csv", ".csv"),
	TEXT_PLAIN_TXT("text/plain", ".txt"),
	
	// Microsoft
    APPLICATION_VNDMSEXCEL_XLS("application/vnd.ms-excel", ".xls"),
	APPLICATION_MSWORD_DOC("application/msword", ".doc"),
	APPLICATION_OPENXML_XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx"),
	APPLICATION_OPENXML_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx");
	

	private String type;
	private String extension;

	MimeType(String type, String extension) {
		this.type = type;
		this.extension = extension;
	}

	/**
	 * Retorna uma String com a representação de um tipo.
	 * 
	 * Exemplo: "application/pdf"
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Retorna a extensão para este tipo.
	 * 
	 * Exemplo: ".pdf"
	 * 
	 * @return
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Retorna o primeiro mime-type encontrado com o tipo informado.
	 * 
	 * @param type
	 *            Ex. "application/zip"
	 * @return o MimeType
	 */
	public static MimeType fromType(String type) {
		if (type != null) {
			type = type.trim().toLowerCase();
			for (MimeType mt : MimeType.values()) {
				if (mt.getType().equals(type)) {
					return mt;
				}
			}
		}

		return null;
	}

	/**
	 * Retorna o primeiro mime-type encontrado com a extensão informada.
	 * 
	 * @param extension
	 *            Ex. ".zip"
	 * @return o MimeType
	 */
	public static MimeType fromExtension(String extension) {
		if (extension != null) {
			if (!extension.startsWith(".")) {
				extension = "." + extension;
			}
			extension = extension.trim().toLowerCase();
			for (MimeType mt : MimeType.values()) {
				if (mt.getExtension().equals(extension)) {
					return mt;
				}
			}
		}

		return null;
	}
}
