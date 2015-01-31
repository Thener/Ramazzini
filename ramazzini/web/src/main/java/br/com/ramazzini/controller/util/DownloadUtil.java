package br.com.ramazzini.controller.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;


public class DownloadUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor privado.
	 */
	private DownloadUtil() {
		super();
	}

	/**
	 * 
	 * @param bytes
	 * @param nomeArquivo
	 */
	public static synchronized void download(byte[] bytes, String nomeArquivo, MimeType mimeType) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {
			response.setContentType(mimeType.getType());
			response.addHeader("Content-disposition", "attachment;filename=" + nomeArquivo + mimeType.getExtension());
			response.getOutputStream().write(bytes);
			context.renderResponse();
			context.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
